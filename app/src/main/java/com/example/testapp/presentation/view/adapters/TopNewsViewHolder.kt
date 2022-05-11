package com.example.testapp.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.databinding.TopNewsHolderBinding
import com.example.testapp.model.entities.News

class TopNewsViewHolder(private val binding: TopNewsHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(listOfNews: List<News>) {
        binding.topNewsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        var topNewsAdapter = TopNewsItemAdapter()
        binding.topNewsViewPager.adapter = topNewsAdapter

        topNewsAdapter.submitList(listOfNews)
    }

}