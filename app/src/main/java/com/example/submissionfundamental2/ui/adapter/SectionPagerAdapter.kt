package com.example.submissionfundamental2.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submissionfundamental2.R
import com.example.submissionfundamental2.ui.detail.FollwersFragment
import com.example.submissionfundamental2.ui.detail.FollwingFragment

class SectionPagerAdapter(
    private val mContex: Context,
    fragmentManager: FragmentManager,
    data: Bundle
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragmentBundle: Bundle = data

    init {
        fragmentBundle = data
    }

    @StringRes
    private val tabName = intArrayOf(R.string.following, R.string.followers)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollwingFragment()
            1 -> fragment = FollwersFragment()
        }

        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContex.resources.getString(tabName[position])
    }
}