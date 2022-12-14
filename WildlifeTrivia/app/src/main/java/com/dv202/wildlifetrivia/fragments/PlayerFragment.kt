package com.dv202.wildlifetrivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dv202.wildlifetrivia.R
import com.dv202.wildlifetrivia.databinding.FragmentPlayerBinding
import com.dv202.wildlifetrivia.services.DataService

class PlayerFragment : Fragment() {

    private lateinit var _binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.playButton.setOnClickListener {
            DataService.setPlayerName(_binding.playerNameInput.text.toString())

            findNavController().navigate(R.id.categoriesFragment4)
        }
    }
}


//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import com.dv202.wildlifetrivia.databinding.FragmentWelcomeBinding
//import com.dv202.wildlifetrivia.services.DataService
//import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
//
//
//class WelcomeFragment : Fragment() {
//
//    private lateinit var binding: FragmentWelcomeBinding
//    private lateinit var playButton: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
//
//        playButton = binding.playButton
//
//        playButton.setOnClickListener {
//            val name = binding.playerName.text.toString()
//            val nameIsValid = name.nonEmpty()
//
//            if (nameIsValid) {
//                findNavController().navigate(R.id.categoriesFragment)
//                DataService.setPlayerName(name)
//            } else {
//                Toast.makeText(activity, "Player name is required", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        return binding.root
//
//    }
//}