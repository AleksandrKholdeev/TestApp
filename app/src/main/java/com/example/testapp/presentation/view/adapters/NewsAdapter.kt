package com.example.testapp.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.NewsItemBinding
import com.example.testapp.databinding.TopNewsHolderBinding
import com.example.testapp.model.entities.News

class NewsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var news: ArrayList<News> = ArrayList()
    private var topNews: ArrayList<News> = ArrayList()
    private lateinit var holder: TopNewsViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == 1) {
            val binding = TopNewsHolderBinding.inflate(layoutInflater, parent, false)
            return TopNewsViewHolder(binding)
        } else {
            val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
            return NewsViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 1) {
            this.holder = holder as TopNewsViewHolder
            (holder as TopNewsViewHolder).bind(topNews)
        } else {
            (holder as NewsViewHolder).bind(news[position])
        }
    }

    override fun getItemCount(): Int = news.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 1
        } else {
            return 2
        }
    }

    fun addNews(news: List<News>) {
        this.news.addAll(news)

        for (temp in news) {
            if (temp.isTop) {
                topNews.add(temp)
            }
        }

        notifyDataSetChanged()
    }

}