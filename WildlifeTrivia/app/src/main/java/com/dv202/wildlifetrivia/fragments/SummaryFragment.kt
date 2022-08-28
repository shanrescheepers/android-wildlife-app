package com.dv202.wildlifetrivia.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dv202.wildlifetrivia.R
import com.dv202.wildlifetrivia.databinding.FragmentSummaryBinding
import com.dv202.wildlifetrivia.models.AnswerAdapter
import com.dv202.wildlifetrivia.models.QuestionDto
import com.dv202.wildlifetrivia.services.DataService

class SummaryFragment : Fragment() {
    private lateinit var _binding: FragmentSummaryBinding
    private lateinit var summaryAdapter: AnswerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)

        _binding.homeButton.setOnClickListener {
            DataService.reset()
            findNavController().navigate(R.id.playerFragment)

        }

        return _binding.root
    }
    override fun onStart() {
        super.onStart()

        val recyclerView: RecyclerView = _binding.summaryView
        summaryAdapter = AnswerAdapter(DataService.getQuestions()!!, DataService.getAnswers()!!)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = summaryAdapter
        summaryAdapter.notifyDataSetChanged()

        if (DataService.getTotalPoints() > getHighScore()) {
            setHighScore()
        }
    }
    private fun setHighScore() {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString(DataService.getCategory(),"${DataService.getPlayerName()}")
        editor.putInt(DataService.getCategory(), DataService.getTotalPoints())
        editor.commit()
    }
    private fun getHighScore(): Int {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        return sharedPreference.getInt(DataService.getCategory(), 0)
    }
    //Move na die appbar ding toe
    // require activity -> this of context of Context
    fun clearHighscores() {
        val sharedPreference =  requireActivity().getSharedPreferences("Highscore", Context.MODE_PRIVATE)
        sharedPreference.edit().clear().commit()
    }
}