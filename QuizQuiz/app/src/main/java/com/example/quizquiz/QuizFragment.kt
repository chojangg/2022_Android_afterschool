package com.example.quizquiz

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizquiz.database.Quiz
import com.example.quizquiz.database.QuizDatabase

class QuizFragment : Fragment(),
    QuizStartFragment.QuizStartListener
{
    lateinit var db : QuizDatabase
    lateinit var quizList : List<Quiz>
    var currentQuizIdx = 0
    var correctCount = 0

    override fun onQuizStart() {
        Log.d("mytag", "시작하기!!!")

        AsyncTask.execute {
            currentQuizIdx = 0
            correctCount = 0
            quizList = db.quizDAO().getAll()

            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,
                    QuizSolveFragment.newInstance(quizList[currentQuizIdx]))
                .commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.quiz_fragment,
            container,
            false)

        db = QuizDatabase.getInstance(requireContext())

        childFragmentManager
            .beginTransaction().add(R.id.fragment_container, QuizStartFragment())
            .commit()

        return view
    }

}