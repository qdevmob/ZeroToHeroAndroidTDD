package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private var state: State = State.Initial

    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.removeButton)
        textView = findViewById(R.id.titleTextView)
        linearLayout = findViewById(R.id.rootLayout)



        button.setOnClickListener {
            state = State.Remove
            state.apply(linearLayout, textView, button)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("state",state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getSerializable("state")
        state.apply(linearLayout, textView, button)
    }
}

interface State:Serializable{
    fun apply(linearLayout: LinearLayout,textView: TextView,button: Button)
    object Initial:State{
        override fun apply(linearLayout: LinearLayout,textView: TextView,button: Button)=Unit
    }
    object Remove:State{
        override fun apply(linearLayout: LinearLayout,textView: TextView,button: Button){
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}