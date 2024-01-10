package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial
    private lateinit var textView: TextView
    private lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout = findViewById<LinearLayout>(R.id.rootLayout)
        textView = findViewById<TextView>(R.id.titleTextView)
        val button = findViewById<Button>(R.id.removeButton)
        button.setOnClickListener {
            state = State.Removed
            state.applyRemove(layout, textView)

        }
    }override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("state", state)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getSerializable("state")?.let { state = it as State }
        state.applyRemove(layout, textView)
    }
}

interface State : Serializable {
    fun applyRemove(linearLayout: LinearLayout, textView: TextView)

    object Removed : State {
        override fun applyRemove(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }

    object Initial : State {
        override fun applyRemove(linearLayout: LinearLayout, textView: TextView) = Unit
    }
}





