package com.example.sushiveslatestapp.presentation.login

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.sushiveslatestapp.presentation.base.BaseFragment
import com.example.sushiveslatestapp.App
import com.example.sushiveslatestapp.R
import com.example.sushiveslatestapp.databinding.FragmentLoginBinding
import com.example.sushiveslatestapp.presentation.FragmentLoginInteractor
import com.example.sushiveslatestapp.presentation.NetworkRequestState

class LoginFragment : BaseFragment() {

    private var fragmentInteractor: FragmentLoginInteractor? = null

    private lateinit var viewModel: LoginViewModel

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun initDagger() {
        (requireActivity().application as App).getAppComponent()
            .registerLoginComponent()
            .create()
            .inject(this)
    }

    override fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentLoginInteractor) {
            fragmentInteractor = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()

        viewModel.getCurrentData()

    }

    private fun setObservers() {
        viewModel.dateTime.observe(viewLifecycleOwner) { dateTime ->
            binding.loginTextTime.text = dateTime.time
            binding.loginTextDate.text = dateTime.date
        }
        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            binding.loginTextCelsius.text = weather.celsius
            Glide.with(this).load(weather.iconWeather)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .apply(RequestOptions.circleCropTransform())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        binding.loginVectorWeather.setImageResource(R.drawable.ic_login_cloud)
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        return false
                    }
                }).into(binding.loginVectorWeather)
        }

        viewModel.networkState.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    NetworkRequestState.SUCCESS -> {
                        binding.loginShimmer.isVisible = false
                    }
                    NetworkRequestState.ERROR -> {
                        AlertDialog.Builder(context).setTitle(getString(R.string.error_title))
                            .setMessage(getString(R.string.error_mesege))
                            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                                viewModel.getCurrentData()
                            }
                            .setNegativeButton(getString(R.string.no)) { _, _ ->
                            }.create().show()
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.loginButtonEntrance.setOnClickListener {
            fragmentInteractor?.onLogin()
        }
        binding.loginTextCreateAccount.setOnClickListener {
            fragmentInteractor?.onLogin()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentInteractor = null
    }
}