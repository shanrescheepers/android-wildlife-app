package com.dv202.wildlifetrivia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dv202.wildlifetrivia.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var playerName = ""
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("playerData",
            Context.MODE_PRIVATE)
        this.playerName = sharedPreferences!!.getString("name","Player").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.welcomeName.text = "Welcome, $playerName"

        return binding.root
    }
}