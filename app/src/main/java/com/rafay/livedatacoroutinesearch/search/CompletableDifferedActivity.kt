package com.rafay.livedatacoroutinesearch.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafay.livedatacoroutinesearch.SearchAdapter
import com.rafay.livedatacoroutinesearch.databinding.ActivityCompleteableDifferedBinding

class CompletableDifferedActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(CompletableDifferedViewModel::class.java)
    }

    private lateinit var binding: ActivityCompleteableDifferedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompleteableDifferedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        viewModel.results.observe(this, Observer {
            (binding.recyclerView.adapter as SearchAdapter).submitList(it)
            binding.recyclerView.postDelayed({ binding.recyclerView.scrollToPosition(0) }, 200)
        })

        binding.editSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null){
                viewModel.search(text.toString())
            }
        }

        binding.buttonClose.setOnClickListener {
            viewModel.search("")
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SearchAdapter()
        }
    }
}
