package com.app.mymainapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.mymainapp.ui.home.PeopleFragment
import com.app.mymainapp.ui.home.RoomsFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return PeopleFragment()
            }
            1 -> {
                return RoomsFragment()
            }
            else -> {
                return PeopleFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "People"
            }
            1 -> {
                return "Rooms"
            }

        }
        return super.getPageTitle(position)
    }

}