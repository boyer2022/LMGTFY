package com.example.lmgtfy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    // References to the components
        // EditText is validation that the text has changed(edited)
    private lateinit var searchText : EditText
    private lateinit var searchButton: Button
    private lateinit var searchConfirmation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.enter_search_text)   // Echos search text
        searchButton = findViewById(R.id.search_button)
        searchConfirmation = findViewById(R.id.show_search_text)

        // Use the search text to use for the confirmation text
        searchText.doAfterTextChanged {
            // Calling a function to do activity
           echoUserSearchTerm()
        }
        // Launching the web browser
        searchButton.setOnClickListener {
            // New function for what the onClickListener does
            launchSearch()
        }
    }
    // Function for onClickListener
    private fun launchSearch() {
        val text = searchText.text
        if (text.isBlank()) {
            // show user a message to enter text
            Toast.makeText(this, getString(R.string.no_text_error_message),
                Toast.LENGTH_SHORT).show()
        } else {
            // show a Toast confirmation
            Toast.makeText(this, getString(R.string.searching_confirmation_message,text),
                Toast.LENGTH_LONG).show()
            // Call function for browser activity
            // (parameter) to search for
            googleSearch(text.toString())
        }
    }

    // Function for google search
        // function NAME_OF_FUNCTION(parameter: Return type) {}
    private fun googleSearch(text: String) {
        // launch web browser to search Google
        val webAddress = "https://www.google.com/search?q=$text"
        val uri = Uri.parse(webAddress)
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        // Asking Android to start an activity (Using the web browser)
        startActivity(browserIntent)
    }

// Function to search with validation confirmation text
    private fun echoUserSearchTerm() {
        val text = searchText.text
        if (text.isNotBlank()) {
            searchConfirmation.text = getString(R.string.search_display_text)
        } else {
            searchConfirmation.text = getString(R.string.search_display_text, "...")
        }
    }
}