package com.rafay.livedatacoroutinesearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafay.livedatacoroutinesearch.databinding.ActivityMainBinding
import com.rafay.livedatacoroutinesearch.search.CoroutineScopeActivity
import com.rafay.livedatacoroutinesearch.search.CompletableDifferedActivity
import com.rafay.livedatacoroutinesearch.search.ViewModelScopeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.buttonMethodThree.setOnClickListener {
            Intent(this, ViewModelScopeActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}
