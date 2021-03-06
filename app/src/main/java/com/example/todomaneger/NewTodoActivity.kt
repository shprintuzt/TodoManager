package com.example.todomaneger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewTodoActivity : AppCompatActivity() {

    private lateinit var editTodoView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
        editTodoView = findViewById(R.id.edit_todo)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTodoView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTodoView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, title)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.todolistsql.REPLY"
    }
}
