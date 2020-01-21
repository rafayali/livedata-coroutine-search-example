package com.rafay.livedatacoroutinesearch.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafay.livedatacoroutinesearch.R
import com.rafay.livedatacoroutinesearch.SearchAdapter
import com.rafay.livedatacoroutinesearch.databinding.ActivityMethodOneBinding

class MethodOneActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(MethodOneViewModel::class.java)
    }

    private lateinit var binding: ActivityMethodOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_method_one)

        setupRecyclerView()

        viewModel.results.observe(this, Observer {
            (binding.recyclerView.adapter as SearchAdapter).submitList(it)
            binding.recyclerView.postDelayed({ binding.recyclerView.scrollToPosition(0) }, 200)
        })

        binding.editSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                viewModel.search(text.toString())
            }
        }

        binding.buttonClose.setOnClickListener {
            binding.editSearch.setText("")
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SearchAdapter()
        }
    }
}
