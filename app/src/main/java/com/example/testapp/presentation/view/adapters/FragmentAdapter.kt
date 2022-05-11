package com.example.testapp.presentation.view.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapp.presentation.view.FavouritesFragment
import com.example.testapp.presentation.view.NewsFragment
import com.example.testapp.presentation.view.VideosFragment

class FragmentAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(i: Int): Fragment {
        val fragment =
            when (i) {
                0 -> NewsFragment()
                1 -> VideosFragment()
                2 -> FavouritesFragment()
                else -> NewsFragment()
            }

        return fragment
    }

}