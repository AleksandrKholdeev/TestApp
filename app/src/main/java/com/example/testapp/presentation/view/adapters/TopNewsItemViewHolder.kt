package com.example.testapp.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.TopNewsItemBinding
import com.example.testapp.model.entities.News
import com.squareup.picasso.Picasso

class TopNewsItemViewHolder(private val binding: TopNewsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.topNewsImage.setImageDrawable(null)
        if (!news.imgUrl.isEmpty()) {
            Picasso.get()
                .load(news.imgUrl)
                .fit()
                .centerCrop()
                .into(binding.topNewsImage)
        }
        binding.topNewsTitle.text = news.newsTitle
        binding.topNewsWebSite.text = news.websiteUrl
        binding.topNewsTime.text = news.postedTime
    }
}