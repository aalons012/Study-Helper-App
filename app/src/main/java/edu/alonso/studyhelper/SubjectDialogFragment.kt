package edu.alonso.studyhelper

// Import necessary Android and androidx classes
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

// A dialog fragment for creating a new subject
class SubjectDialogFragment: DialogFragment() {

    // Interface for the listener that receives the new subject text
    interface OnSubjectEnteredListener {
        // Called when the user enters a new subject
        fun onSubjectEntered(subjectText: String)
    }

    // The listener that will be notified when a subject is entered
    private lateinit var listener: OnSubjectEnteredListener

    // Called to create the dialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create an EditText for the user to enter the subject name
        val subjectEditText = EditText(requireActivity())
        // Set the input type to plain text
        subjectEditText.inputType = InputType.TYPE_CLASS_TEXT
        // Limit the input to a single line
        subjectEditText.maxLines = 1
        // Build and return the AlertDialog
        return AlertDialog.Builder(requireActivity())
            // Set the dialog title
            .setTitle(R.string.subject)
            // Set the custom view to our EditText
            .setView(subjectEditText)
            // Set the positive button (Create)
            .setPositiveButton(R.string.create) { dialog, whichButton ->
                // Get the subject text from the EditText
                val subject = subjectEditText.text.toString()
                // Notify the listener with the trimmed subject text
                listener.onSubjectEntered(subject.trim())
            }
            // Set the negative button (Cancel)
            .setNegativeButton(R.string.cancel, null)
            // Create the dialog
            .create()
    }

    // Called when the fragment is attached to a context
    override fun onAttach(context: Context) {
        // Call the superclass implementation
        super.onAttach(context)
        // Set the listener to the hosting context
        listener = context as OnSubjectEnteredListener
    }
}
