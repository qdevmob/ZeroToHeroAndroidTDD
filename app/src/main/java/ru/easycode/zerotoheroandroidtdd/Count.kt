package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.TextView


private const val ZERO = 0

interface Count {

    fun increment(number: String): UiState
    class Base(private val step: Int, private val max: Int) : Count {
        init {
            if (step <= ZERO) throw IllegalStateException("step should be positive, but was $step")
            if (max <= ZERO) throw IllegalStateException("max should be positive, but was $max")
            if (step > max) throw IllegalStateException("max should be more than step")
        }

        override fun increment(number: String): UiState {
            val count = number.toInt() + step
            return if (noNextIncrement(count))
                UiState.Max(text = count.toString())
            else
                UiState.Base(text = count.toString())
        }

        private fun noNextIncrement(number: Int): Boolean =
            (number + step) > max
    }

}

sealed interface UiState {

    val text: String
    fun isMax(): Boolean
    data class Base(override val text: String) : UiState {
        // Kiparo

        override fun isMax(): Boolean = false
    }
    data class Max(override val text: String) : UiState {

        override fun isMax(): Boolean = true
    }
}
