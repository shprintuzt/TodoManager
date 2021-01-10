package com.example.todomaneger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewTodoActivity : AppCompatActivity() {

    private lateinit var editTitleView: EditText
    private lateinit var editDetailView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        editTitleView = findViewById(R.id.edit_title)
        editDetailView = findViewById(R.id.edit_detail)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitleView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTitleView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_TITLE, title)
                val detail = editDetailView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_DETAIL, detail)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_TITLE = "com.example.android.todolistsql.REPLY_TITLE"
        const val EXTRA_REPLY_DETAIL = "com.example.android.todolistsql.REPLY_TITLE"
    }
}
