package com.dv202.wildlifetrivia

import android.content.Context
import android.content.SharedPreferences
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
            findNavController().navigate(R.id.categoriesFragment)
            val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("playerData",
                Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor =  sharedPreferences!!.edit()

            val name = binding.playerName.text

            editor.putString("name", name.toString())
            editor.apply()
            editor.commit()
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