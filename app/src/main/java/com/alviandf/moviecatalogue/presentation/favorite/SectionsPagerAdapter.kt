package com.alviandf.moviecatalogue.presentation.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

//    override fun createFragment(position: Int): Fragment {
//        var fragment: Fragment? = null
//        when (position) {
//            0 -> fragment = HomeFragment()
//            1 -> fragment = ProfileFragment()
//        }
//        return fragment as Fragment
//    }
//
//    override fun getItemCount(): Int {
//        return 2
//    }

//    override fun getCount() = 2
//
//    override fun getItem(position: Int): Fragment {
//        return FollowFragment.newInstance(position + 1)
//    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return MoviesOrTvShowsFragment.newInstance(position + 1)
    }
}


