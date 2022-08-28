package com.dv202.wildlifetrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentCategoriesBinding
import com.example.quiz.models.QuestionDto
import com.example.quiz.services.DataService

class CategoriesFragment : Fragment(), View.OnClickListener {

    private lateinit var _binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val name = DataService.getPlayerName().ifEmpty { "Guest" }
        _binding.welcomePlayerTitle.text = "Welcome $name"

        _binding.animalCategory.setOnClickListener(this)
        _binding.plantsCategory.setOnClickListener(this)
        _binding.bothCategory.setOnClickListener(this)

        _binding.animalHS.text = getAnimalHighScore().toString()
        _binding.plantHS.text = getPlantHighScore().toString()
        _binding.bothHS.text = getBothHighScore().toString()

        return _binding.root
    }

    override fun onClick(view: View?) {

        val buttonClicked = view as Button

        DataService.setCategory(buttonClicked.text.toString())

        when (view?.id) {
            _binding.animalCategory.id -> {
                val animalQuestions = DataService.getFaunaQuestions()

                if (animalQuestions!![0].options == null) {
                    findNavController().navigate(R.id.inputFragment)
                } else {
                    findNavController().navigate(R.id.multipleChoiceFragment)
                }
            }
            _binding.plantsCategory.id -> {
                val plantQuestions = DataService.getFLoraQuestions()

                if (plantQuestions!![0].options == null) {
                    findNavController().navigate(R.id.inputFragment)
                } else {
                    findNavController().navigate(R.id.multipleChoiceFragment)
                }
            }
            _binding.bothCategory.id -> {
                val allQuestions = DataService.getQuestions()

                if (allQuestions!![0].options == null) {
                    findNavController().navigate(R.id.inputFragment)
                } else {
                    findNavController().navigate(R.id.multipleChoiceFragment)
                }
            }
        }
    }

    private fun getBothHighScore(): Int {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        return sharedPreference.getInt("Both", 0)
    }

    private fun getAnimalHighScore(): Int {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        return sharedPreference.getInt("Animals", 0)
    }

    private fun getPlantHighScore(): Int {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        return sharedPreference.getInt("Plants", 0)
    }

}