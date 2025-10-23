package edu.alonso.studyhelper

// Import necessary Android and androidx classes
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import edu.alonso.studyhelper.model.Question
import edu.alonso.studyhelper.model.Subject
import edu.alonso.studyhelper.viewmodel.QuestionListViewModel

// The activity for displaying questions of a subject
class QuestionActivity : AppCompatActivity() {

    // ViewModel for managing the list of questions
    private lateinit var questionListViewModel: QuestionListViewModel
    // The subject whose questions are being displayed
    private lateinit var subject: Subject
    // The list of questions for the current subject
    private lateinit var questionList: List<Question>
    // TextView for the "Answer" label
    private lateinit var answerLabelTextView: TextView
    // TextView to display the answer to a question
    private lateinit var answerTextView: TextView
    // Button to show or hide the answer
    private lateinit var answerButton: Button
    // TextView to display the question text
    private lateinit var questionTextView: TextView
    // Layout that is visible when there are questions to show
    private lateinit var showQuestionLayout: ViewGroup
    // Layout that is visible when there are no questions
    private lateinit var noQuestionLayout: ViewGroup
    // Index of the currently displayed question in the questionList
    private var currentQuestionIndex = 0

    // Companion object to hold constants for intent extras
    companion object {
        // Key for the subject ID passed via intent
        const val EXTRA_SUBJECT_ID = "edu.alonso.studyhelper.subject_id"
        // Key for the subject text passed via intent
        const val EXTRA_SUBJECT_TEXT = "edu.alonso.studyhelper.subject_text"
    }

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass implementation
        super.onCreate(savedInstanceState)
        // Set the content view to the question activity layout
        setContentView(R.layout.activity_question)

        // Initialize the views from the layout
        questionTextView = findViewById(R.id.question_text_view)
        answerLabelTextView = findViewById(R.id.answer_label_text_view)
        answerTextView = findViewById(R.id.answer_text_view)
        answerButton = findViewById(R.id.answer_button)
        showQuestionLayout = findViewById(R.id.show_question_layout)
        noQuestionLayout = findViewById(R.id.no_question_layout)

        // Set click listeners for the buttons
        answerButton.setOnClickListener { toggleAnswerVisibility() }
        findViewById<Button>(R.id.add_question_button).setOnClickListener { addQuestion() }

        // Get the subject ID and text from the intent extras
        val subjectId = intent.getLongExtra(EXTRA_SUBJECT_ID, 0)
        val subjectText = intent.getStringExtra(EXTRA_SUBJECT_TEXT)
        // Create a Subject object
        subject = Subject(subjectId, subjectText!!)

        // Initialize the ViewModel
        questionListViewModel = QuestionListViewModel(application)
        // Get the list of questions for the current subject
        questionList = questionListViewModel.getQuestions(subjectId)

        // Update the UI to display the first question or the "no questions" message
        updateUI()
    }

    // Updates the UI based on whether there are questions or not
    private fun updateUI() {
        // Attempt to show the current question
        showQuestion(currentQuestionIndex)
        // If the question list is empty
        if (questionList.isEmpty()) {
            // Update the app bar title
            updateAppBarTitle()
            // Show the "no questions" layout
            displayQuestion(false)
        } else {
            // Otherwise, show the question layout
            displayQuestion(true)
        }
    }

    // Inflate the options menu from the menu resource file
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.question_menu, menu)
        return true
    }

    // Handle clicks on the options menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Determine which app bar item was chosen
        return when (item.itemId) {
            // If "previous" is selected
            R.id.previous -> {
                // Show the previous question
                showQuestion(currentQuestionIndex - 1)
                true
            }
            // If "next" is selected
            R.id.next -> {
                // Show the next question
                showQuestion(currentQuestionIndex + 1)
                true
            }
            // If "add" is selected
            R.id.add -> {
                // Call the addQuestion function
                addQuestion()
                true
            }
            // If "edit" is selected
            R.id.edit -> {
                // Call the editQuestion function
                editQuestion()
                true
            }
            // If "delete" is selected
            R.id.delete -> {
                // Call the deleteQuestion function
                deleteQuestion()
                true
            }
            // For any other item, fall back to the default behavior
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Toggles the visibility of the question and "no question" layouts
    private fun displayQuestion(display: Boolean) {
        if (display) {
            // If true, show the question layout and hide the "no question" layout
            showQuestionLayout.visibility = View.VISIBLE
            noQuestionLayout.visibility = View.GONE
        } else {
            // If false, hide the question layout and show the "no question" layout
            showQuestionLayout.visibility = View.GONE
            noQuestionLayout.visibility = View.VISIBLE
        }
    }

    // Updates the app bar title with the subject and question count
    private fun updateAppBarTitle() {
        // Display subject and number of questions in app bar
        val title = resources.getString(R.string.question_number, subject.text,
            currentQuestionIndex + 1, questionList.size)
        // Set the activity's title
        setTitle(title)
    }

    // Placeholder function to handle adding a new question
    private fun addQuestion() {
        // TODO: Add question
    }

    // Placeholder function to handle editing the current question
    private fun editQuestion() {
        // TODO: Edit question
    }

    // Placeholder function to handle deleting the current question
    private fun deleteQuestion() {
        // TODO: Delete question
    }

    // Displays a question at a specific index in the list
    private fun showQuestion(questionIndex: Int) {
        // Check if there are any questions to show
        if (questionList.isNotEmpty()) {
            var newQuestionIndex = questionIndex
            // If the index is out of bounds, wrap around
            if (questionIndex < 0) {
                // If it's negative, go to the last question
                newQuestionIndex = questionList.size - 1
            } else if (questionIndex >= questionList.size) {
                // If it's too high, go to the first question
                newQuestionIndex = 0
            }

            // Update the current question index
            currentQuestionIndex = newQuestionIndex
            // Update the app bar title to reflect the new question number
            updateAppBarTitle()

            // Get the question at the current index
            val question = questionList[currentQuestionIndex]
            // Set the text of the question and answer TextViews
            questionTextView.text = question.text
            answerTextView.text = question.answer
        } else {
            // If there are no questions, set the index to -1
            currentQuestionIndex = -1
        }
    }

    // Toggles the visibility of the answer
    private fun toggleAnswerVisibility() {
        // If the answer is currently visible
        if (answerTextView.visibility == View.VISIBLE) {
            // Change the button text to "Show Answer"
            answerButton.setText(R.string.show_answer)
            // Hide the answer TextView and its label
            answerTextView.visibility = View.INVISIBLE
            answerLabelTextView.visibility = View.INVISIBLE
        } else {
            // If the answer is hidden, change the button text to "Hide Answer"
            answerButton.setText(R.string.hide_answer)
            // Show the answer TextView and its label
            answerTextView.visibility = View.VISIBLE
            answerLabelTextView.visibility = View.VISIBLE
        }
    }
}
