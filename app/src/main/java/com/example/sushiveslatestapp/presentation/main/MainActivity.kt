package com.example.sushiveslatestapp.presentation.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.sushiveslatestapp.R
import com.example.sushiveslatestapp.databinding.ActivityMainBinding
import com.example.sushiveslatestapp.presentation.FragmentLoginInteractor
import com.example.sushiveslatestapp.presentation.dpToPx
import com.example.sushiveslatestapp.presentation.home.HomeFragment
import com.example.sushiveslatestapp.presentation.login.LoginFragment
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.sushiveslatestapp.App
import com.example.sushiveslatestapp.presentation.factory.DaggerViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), FragmentLoginInteractor {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: MenuViewModel

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val animatorSet = AnimatorSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initDagger()
        initViewModule()

        setListeners()
        setObservers()
        initDrawerLayout()
        initNavigation()
        initObjectAnimator()
        initTogle()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LoginFragment()).commit()
        }
    }

    private fun setListeners() {
        binding.menuLogout.setOnClickListener {
            binding.toggleButton.toggle()
            binding.toggleButton.isVisible = false
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment()).commit()
        }
    }

    private fun initDagger() {
        (this.application as App).getAppComponent()
            .registerMenuComponent()
            .create()
            .inject(this)
    }

    private fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }

    private fun setObservers() {
        viewModel.user.observe(this) { user ->
            val header = binding.navigationView.getHeaderView(0)
            val imageUser = header.findViewById<ImageView>(R.id.menuImage)
            val townUser = header.findViewById<TextView>(R.id.menuTown)
            val nameUser = header.findViewById<TextView>(R.id.menuUserName)
            nameUser.text = user.name
            townUser.text = user.town
            Glide.with(this).load(user.urlImage).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions.circleCropTransform())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageUser.setImageResource(R.drawable.image_user)
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }).into(imageUser)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initTogle() {
        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                disableEnableControls(false, binding.container)
                binding.container.background = getDrawable(R.drawable.round_fragment)
                binding.drawerLayout.openDrawer(binding.navigationView)
                animatorSet.start()
            } else {
                animatorSet.reverse()
                binding.container.background = getDrawable(R.color.cardview_light_background)
                binding.drawerLayout.closeDrawer(binding.navigationView)
                disableEnableControls(true, binding.container)
            }
        }
    }

    private fun initNavigation() {
        binding.navigationView.menu.getItem(0).isChecked = true
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.nav_accounts -> Toast.makeText(this, "Accounts", Toast.LENGTH_SHORT).show()
                R.id.nav_transactions -> Toast.makeText(this, "Transaction", Toast.LENGTH_SHORT)
                    .show()
                R.id.nav_starts -> Toast.makeText(this, "Stats", Toast.LENGTH_SHORT).show()
                R.id.nav_settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                R.id.nav_help -> Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun initObjectAnimator() {
        val animatorScaleY =
            ObjectAnimator.ofFloat(binding.container, View.SCALE_Y, 1F, 0.7F).apply {
                duration = 500
                interpolator = LinearInterpolator()
            }
        val animatorScaleX =
            ObjectAnimator.ofFloat(binding.container, View.SCALE_X, 1F, 0.7F).apply {
                duration = 500
                interpolator = LinearInterpolator()
            }
        val animatorRotation =
            ObjectAnimator.ofFloat(binding.container, View.ROTATION, 0.0F, -15F).apply {
                duration = 500
                interpolator = LinearInterpolator()
            }
        val animatorTranslationX =
            ObjectAnimator.ofFloat(binding.container, View.TRANSLATION_X, 0F, 180.dpToPx(this))
                .apply {
                    duration = 500
                    interpolator = LinearInterpolator()
                }

        animatorSet.playTogether(
            animatorScaleY,
            animatorScaleX,
            animatorRotation,
            animatorTranslationX
        )
    }

    private fun initDrawerLayout() {
        val drawerLayout = binding.drawerLayout
        drawerLayout.drawerElevation = 0F
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun onLogin() {
        viewModel.getCurrentData()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment()).commit()
        binding.toggleButton.isVisible = true
    }

    private fun disableEnableControls(enable: Boolean, vg: ViewGroup) {
        for (i in 0 until vg.childCount) {
            val child = vg.getChildAt(i)
            child.isClickable = enable
        }
    }
}