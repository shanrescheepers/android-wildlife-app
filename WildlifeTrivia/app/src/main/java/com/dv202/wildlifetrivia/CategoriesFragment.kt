package com.dv202.wildlifetrivia

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import java.io.IOException

class CategoriesFragment : Fragment() {

    private lateinit var btnPlay : Button
    private lateinit var btnPause : Button
    var mediaPlayer : MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)

        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)

        btnPlay.setOnClickListener{
            playAudio()
        }
       btnPause.setOnClickListener{
           pauseAudio()
       }

    }

    private fun playAudio() {
        val audioURL = "https://www.bensound.com/bensound-music/bensound-enigmatic.mp3"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()

        } catch(e: IOException){
            e.printStackTrace()
        }
        Toast.makeText(this, "Audio Playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }else{
            Toast.makeText(this, "Audio is Paused", Toast.LENGTH_SHORT).show()
        }
    }
}