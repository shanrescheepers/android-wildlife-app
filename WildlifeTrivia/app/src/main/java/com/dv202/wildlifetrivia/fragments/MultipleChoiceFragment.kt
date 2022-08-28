package com.dv202.wildlifetrivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentMultipleChoiceBinding
import com.example.quiz.models.QuestionDto
import com.example.quiz.services.DataService
class MultipleChoiceFragment() : Fragment(), View.OnclickListerner{
    private lateinit var _binding: FragmentMultipleChoiceBinding
    private var question: QuestionDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.question = DataService.getNextQuestion()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultipleChoiceBinding.inflate(inflater, container, false)

        _binding.answer1.setOnClickListener(this)
        _binding.answer2.setOnClickListener(this)
        _binding.answer3.setOnClickListener(this)
        _binding.answer4.setOnClickListener(this)

        setQuestionAndAnswers()
        return _binding.root
    }
    private fun setQuestionAndAnswers() {
        _binding.multiQuestionText.text = question?.question
        _binding.answer1.text = question?.options!![0]
        _binding.answer2.text = question?.options!![1]
        _binding.answer3.text = question?.options!![2]
        _binding.answer4.text = question?.options!![3]

        _binding.multiScore.text = "Score: ${DataService.getTotalPoints()}"
    }
    override fun onClick(view: View?) {
        val buttonClicked = view as Button

        DataService.setAnswer(buttonClicked.text.toString())

        if (buttonClicked.text == question?.answer) {
            DataService.incrementPoints()
        }

        val nextQuestionIsMulti = DataService.checkNextQuestionType()

        if (nextQuestionIsMulti != null) {
            if (nextQuestionIsMulti) findNavController().navigate(R.id.multipleChoiceFragment) else findNavController().navigate(
                R.id.inputFragment)
        } else {
            findNavController().navigate(R.id.summaryFragment)
        }
    }
}