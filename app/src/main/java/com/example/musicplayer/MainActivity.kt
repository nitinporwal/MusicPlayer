package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.music)
        val playBtn = findViewById<ImageButton>(R.id.btn_play)
        val nextBtn = findViewById<ImageButton>(R.id.btn_next)
        val previousBtn = findViewById<ImageButton>(R.id.btn_previous)

        val seekbar = findViewById<SeekBar>(R.id.seekBar)

        seekbar.progress = 0
        seekbar.max = mediaPlayer.duration
        playBtn.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                playBtn.setImageResource(R.drawable.ic_baseline_pause_24)
            }
            else {
                mediaPlayer.pause()
                playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
//            seekbar.progress = 0
        }
    }
}