package com.dv202.wildlifetrivia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dv202.wildlifetrivia.databinding.FragmentWelcomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty


class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var playButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        playButton = binding.playButton

        playButton.setOnClickListener {
            val name = binding.playerName.text.toString()
            val nameIsValid = name.nonEmpty()

            if (nameIsValid) {
                findNavController().navigate(R.id.categoriesFragment)

                val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("playerData",
                    Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor =  sharedPreferences!!.edit()


                editor.putString("name", name)
                editor.apply()
                editor.commit()
            } else {
                Toast.makeText(activity, "Player name is required", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root

    }
}