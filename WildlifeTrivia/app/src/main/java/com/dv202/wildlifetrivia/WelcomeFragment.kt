package com.dv202.wildlifetrivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dv202.wildlifetrivia.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var playButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        playButton = binding.playButton

        playButton.setOnClickListener {
            Toast.makeText(activity, "Hello ${binding.playerName.text}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.categoriesFragment)
        }

        return binding.root

    }



    //TODO
    // create categroies ui
    // Make toast with category names (net buttons)
    // speel rond met styling
    // MVP challenge: Hou button disabled tot naam ingetik is
//    code review
}