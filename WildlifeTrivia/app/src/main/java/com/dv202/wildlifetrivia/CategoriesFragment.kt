package com.dv202.wildlifetrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.dv202.wildlifetrivia.databinding.FragmentCategoriesBinding
import com.dv202.wildlifetrivia.models.QuestionDto
import com.dv202.wildlifetrivia.services.QuestionsService

class CategoriesFragment : Fragment() {

    private var playerName = ""
    private var questions: List<QuestionDto>? = null
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.playerName = QuestionsService.getPlayerName()
        this.questions = QuestionsService.getQuestions()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.welcomeName.text = "Welcome, $playerName"

        binding.category1.setOnClickListener() {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.categoriesFragment, CategoriesFragment())
                .commit();
        }

        return binding.root
    }
}