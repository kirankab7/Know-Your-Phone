package com.grafocrate.knowyourphone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 6 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CpuFragment()
            1 -> DeviceFragment()
            2 -> SystemFragment()
            3 -> BatteryFragment()
            4 -> SensorsFragment()
            //5 -> ThermalFragment()
            5 -> AboutFragment()
            else -> CpuFragment()
        }
    }
}
