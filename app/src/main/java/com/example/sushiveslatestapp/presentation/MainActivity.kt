package com.example.sushiveslatestapp.presentation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import com.example.sushiveslatestapp.R
import com.example.sushiveslatestapp.presentation.home.HomeFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, HomeFragment.newInstance()).commit()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigation: NavigationView = findViewById(R.id.navigationView)

        val container: FragmentContainerView = findViewById(R.id.container)

        // drawerLayout.openDrawer(navigation)
        //drawerLayout.closeDrawer(navigation)
        val ss = ObjectAnimator.ofFloat(container, View.SCALE_Y, 1F, 0.7F).apply {
            duration = 1000
            interpolator = LinearInterpolator()
        }
        val s2 = ObjectAnimator.ofFloat(container, View.SCALE_X, 1F, 0.7F).apply {
            duration = 1000
            interpolator = LinearInterpolator()
        }
        val s3 = ObjectAnimator.ofFloat(container, View.ROTATION, 0.0F, -15F).apply {
            duration = 1000
            interpolator = LinearInterpolator()
        }
        val s4 = ObjectAnimator.ofFloat(container, View.TRANSLATION_X, 0F, 450F).apply {
            duration = 1000
            interpolator = LinearInterpolator()
        }


        drawerLayout.drawerElevation = 0F
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(ss, s2, s3, s4)
        //animatorSet.start()
        //drawerLayout.openDrawer(navigation)


        val listenr = object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

            }

            override fun onDrawerOpened(drawerView: View) {
                animatorSet.start()
            }

            override fun onDrawerClosed(drawerView: View) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    animatorSet.reverse()
                }
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        }
        //navigation.setItemIconSize()
        drawerLayout.addDrawerListener(listenr)

        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}