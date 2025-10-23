package edu.alonso.studyhelper

// Import necessary classes for handling intents, UI, and data
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.alonso.studyhelper.model.Subject
import edu.alonso.studyhelper.viewmodel.SubjectListViewModel

// Main activity for displaying and managing subjects
class SubjectActivity : AppCompatActivity(),
    SubjectDialogFragment.OnSubjectEnteredListener {

    // Adapter for the RecyclerView
    private var subjectAdapter = SubjectAdapter(mutableListOf())
    // RecyclerView to display the list of subjects
    private lateinit var subjectRecyclerView: RecyclerView
    // Array of colors for the subject backgrounds
    private lateinit var subjectColors: IntArray
    // ViewModel for managing subject data
    private lateinit var subjectListViewModel: SubjectListViewModel

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass implementation
        super.onCreate(savedInstanceState)
        // Set the content view to the subject activity layout
        setContentView(R.layout.activity_subject)

        // Initialize the ViewModel
        subjectListViewModel = SubjectListViewModel(application)

        // Get the array of subject colors from resources
        subjectColors = resources.getIntArray(R.array.subjectColors)

        // Set a click listener for the add subject button
        findViewById<FloatingActionButton>(R.id.add_subject_button).setOnClickListener {
            // Handle the click event
            addSubjectClick()
        }

        // Initialize the RecyclerView
        subjectRecyclerView = findViewById(R.id.subject_recycler_view)
        // Set the layout manager to a grid with 2 columns
        subjectRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        // Show the subjects in the UI
        updateUI(subjectListViewModel.getSubjects())
    }

    // Update the UI with the latest list of subjects
    private fun updateUI(subjectList: List<Subject>) {
        // Create a new adapter with the updated list
        subjectAdapter = SubjectAdapter(subjectList as MutableList<Subject>)
        // Set the new adapter for the RecyclerView
        subjectRecyclerView.adapter = subjectAdapter
    }

    // Callback method from the SubjectDialogFragment
    override fun onSubjectEntered(subjectText: String) {
        // Check if the entered text is not empty
        if (subjectText.isNotEmpty()) {
            // Create a new Subject object
            val subject = Subject(0, subjectText)
            // Add the new subject via the ViewModel
            subjectListViewModel.addSubject(subject)
            // Update the UI to show the new subject
            updateUI(subjectListViewModel.getSubjects())

            // Show a confirmation toast message
            Toast.makeText(this, "Added $subjectText", Toast.LENGTH_SHORT).show()
        }
    }

    // Handles the click on the add subject button
    private fun addSubjectClick() {
        // Create an instance of the dialog fragment
        val dialog = SubjectDialogFragment()
        // Show the dialog
        dialog.show(supportFragmentManager, "subjectDialog")
    }

    // ViewHolder for displaying a single subject item in the RecyclerView
    private inner class SubjectHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        // It extends RecyclerView.ViewHolder and implements View.OnClickListener
        RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_items, parent, false)),
        View.OnClickListener {

        // The subject being displayed
        private var subject: Subject? = null
        // The TextView that shows the subject's name
        private val subjectTextView: TextView

        // Initialize the ViewHolder
        init {
            // Set the click listener for the item view
            itemView.setOnClickListener(this)
            // Find the TextView in the layout
            subjectTextView = itemView.findViewById(R.id.subject_text_view)
        }

        // Bind a subject to the ViewHolder's views
        fun bind(subject: Subject, position: Int) {
            // Store the subject
            this.subject = subject
            // Set the subject's name in the TextView
            subjectTextView.text = subject.text

            // Set the background color based on the subject's name length
            val colorIndex = subject.text.length % subjectColors.size
            // Apply the color to the TextView's background
            subjectTextView.setBackgroundColor(subjectColors[colorIndex])
        }

        // Handle clicks on the subject item
        override fun onClick(view: View) {
            // Create an intent to start the QuestionActivity
            val intent = Intent(this@SubjectActivity, QuestionActivity::class.java)
            // Pass the subject's ID and text as extras
            intent.putExtra(QuestionActivity.EXTRA_SUBJECT_ID, subject!!.id)
            intent.putExtra(QuestionActivity.EXTRA_SUBJECT_TEXT, subject!!.text)

            // Start the QuestionActivity
            startActivity(intent)
        }
    }

    // Adapter for the RecyclerView that manages the subject items
    private inner class SubjectAdapter(private val subjectList: MutableList<Subject>) :
        RecyclerView.Adapter<SubjectHolder>() {

        // Called when a new ViewHolder is needed
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
            // Inflate the item layout
            val layoutInflater = LayoutInflater.from(applicationContext)
            // Return a new SubjectHolder
            return SubjectHolder(layoutInflater, parent)
        }

        // Called to display the data at a specific position
        override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
            // Bind the subject at the given position to the ViewHolder
            holder.bind(subjectList[position], position)
        }

        // Return the total number of items in the list
        override fun getItemCount(): Int {
            return subjectList.size
        }
    }
}
