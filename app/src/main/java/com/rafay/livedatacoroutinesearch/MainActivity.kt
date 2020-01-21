package com.rafay.livedatacoroutinesearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rafay.livedatacoroutinesearch.databinding.ActivityMainBinding
import com.rafay.livedatacoroutinesearch.search.CoroutineScopeActivity
import com.rafay.livedatacoroutinesearch.search.CompletableDifferedActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonMethodOne.setOnClickListener {
            Intent(this, CoroutineScopeActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.buttonMethodTwo.setOnClickListener {
            Intent(this, CompletableDifferedActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}
