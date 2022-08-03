package com.example.quizquiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.quizquiz.database.Quiz

class QuizSolveFragment : Fragment() {
    interface QuizSolveListener {
        fun onAnswerSelected(isCorrect: Boolean)
    }
    lateinit var listener : QuizSolveListener
    lateinit var quiz : Quiz

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(parentFragment is QuizSolveListener) {
            listener = parentFragment as QuizSolveListener
        } else {
            throw Exception("QuizSolveListener 미구현")
        }
    }

    // newInstance 클래스 메서드 (퀴즈 객체를 전달받도록 구현)
    companion object {
        fun newInstance(quiz: Quiz) : QuizSolveFragment {
            val fragment = QuizSolveFragment()

            val args = Bundle()
            args.putParcelable("quiz", quiz)
            fragment.arguments = args

            return fragment
        }
    }

}