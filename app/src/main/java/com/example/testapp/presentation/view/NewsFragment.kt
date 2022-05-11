package com.example.testapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.NewsApp
import com.example.testapp.databinding.FragmentNewsBinding
import com.example.testapp.presentation.utils.hideKeyboard
import com.example.testapp.presentation.view.adapters.NewsAdapter
import com.example.testapp.presentation.viewModel.*
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModelImpl

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity().application as NewsApp)
                .myComponent.getViewModel(this)

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            newsAdapter = NewsAdapter()
            adapter = newsAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        recyclerView.hideKeyboard()
                    }
                }
            })
        }

        binding.newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send("")
                    }
                }
            }
        })


        if (savedInstanceState == null) {
            lifecycleScope.launch {
                viewModel.queryChannel.send("")
            }
        }
        viewModel.searchResult.observe(viewLifecycleOwner, ::handleResult)
    }

    private fun handleResult(result: NewsResult) {
        Log.d("NewsFragmentLog", result.toString())
        when (result) {
            is ValidResult -> {
                newsAdapter.addNews(result.result)
            }
            is ErrorResult -> {
            }
            is EmptyResult -> {
            }
            is EmptyQuery -> {
            }
            is TerminalError -> {
            }
        }
    }

}