package com.gsanthosh91.app.ui.main

import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gsanthosh91.app.R
import com.gsanthosh91.app.base.BaseActivity
import com.gsanthosh91.app.databinding.ActivityMainBinding
import com.gsanthosh91.app.ui.splash.SplashActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainIPresenter.MainIView, Toolbar.OnMenuItemClickListener {

    private var presenter: MainPresenter<MainActivity> = MainPresenter()
    private lateinit var binding: ActivityMainBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        presenter.attachView(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.appBarMain.toolbar)
        binding.navView.setNavigationItemSelectedListener(this)
        binding.appBarMain.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.appBarMain.toolbar.setOnMenuItemClickListener(this)

        val adapter = TabPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(HomeFragment(), "Profile")
        adapter.addFragment(HomeFragment(), "Settings")
        binding.appBarMain.container.adapter = adapter;
        TabLayoutMediator(
            binding.appBarMain.tabs,
            binding.appBarMain.container
        ) { tab: TabLayout.Tab, position: Int -> tab.text = adapter.getTitle(position) }.attach()
    }


    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            /*R.id.menu -> {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START)
                }
            }*/
        }
    }

    private class TabPagerAdapter internal constructor(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle) {

        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mTitleList: MutableList<String> = ArrayList()

        override fun createFragment(position: Int): Fragment {
            return mFragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mTitleList.add(title)
        }

        fun getTitle(pos: Int): String {
            return mTitleList[pos]
        }

        override fun getItemCount(): Int {
            return mFragmentList.size
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> startActivity(Intent(activity(), SplashActivity::class.java))
        }

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return false
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                Toast.makeText(activity(), "fav", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}