package com.example.testapp.presentation.view.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.NewsItemBinding
import com.example.testapp.model.entities.News
import com.squareup.picasso.Picasso

class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.newsImage.setImageDrawable(null)
        if (!news.imgUrl.isEmpty()) {
            Picasso.get()
                .load(news.imgUrl)
                .fit()
                .error(R.drawable.ic_baseline_web_24)
                .placeholder(R.drawable.ic_baseline_web_24)
                .centerCrop()
                .into(binding.newsImage)
        }
        binding.newsTitle.text = news.newsTitle
        binding.newsWebSite.text = news.websiteUrl
        binding.newsTime.text = news.postedTime
    }

}