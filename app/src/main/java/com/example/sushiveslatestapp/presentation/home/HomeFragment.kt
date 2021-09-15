package com.example.sushiveslatestapp.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sushiveslatestapp.presentation.base.BaseFragment
import com.example.sushiveslatestapp.App
import com.example.sushiveslatestapp.R
import com.example.sushiveslatestapp.databinding.FragmentHomeBinding
import com.example.sushiveslatestapp.presentation.NetworkRequestState
import com.example.sushiveslatestapp.presentation.home.services.UsersAdapter
import com.example.sushiveslatestapp.presentation.home.services.UsersItemDecoration
import java.text.NumberFormat
import java.util.*

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    private var usersAdapter: UsersAdapter? = null
    private var servicesAdapter: ServicesAdapter? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun initDagger() {
        (requireActivity().application as App).getAppComponent()
            .registerHomeComponent()
            .create()
            .inject(this)
    }

    override fun initViewModule() {
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        initRecyclerUsers()
        initRecyclerServices()
        initData()
    }

    private fun initData() {
        viewModel.getCurrentData()
    }

    private fun setObservers() {
        viewModel.balance.observe(viewLifecycleOwner) { balance ->
            binding.homeBalance.text = NumberFormat.getNumberInstance(Locale.US).format(balance)
        }
        viewModel.listUsers.observe(viewLifecycleOwner) { listUsers ->
            usersAdapter?.setListUsers(listUsers)
        }
        viewModel.listServices.observe(viewLifecycleOwner) { listService ->
            servicesAdapter?.setListUsers(listService)
        }
        viewModel.networkState.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    NetworkRequestState.SUCCESS -> {
                        binding.shimmerBalance.isVisible = false
                        binding.shimmerServices.isVisible = false
                        binding.shimmerUsers.isVisible = false
                        binding.homeBalance.isVisible = true
                        binding.homeRecyclerSevice.isVisible = true
                        binding.homeRecyclerUsers.isVisible = true
                    }
                    NetworkRequestState.ERROR -> {
                        AlertDialog.Builder(context).setTitle("Ошибка!")
                            .setMessage("Данные не пришли. Повторить запрос?")
                            .setPositiveButton("Да") { dialog, id ->
                                viewModel.getCurrentData()
                            }
                            .setNegativeButton("Нет") { dialog, id ->
                            }.create().show()
                    }
                }
            }
        }
    }


    private fun initRecyclerUsers() {
        usersAdapter = UsersAdapter(onClickButton =
        {
            Toast.makeText(context, getString(R.string.toast_add_users), Toast.LENGTH_SHORT).show()
        },
            onClickUsers = { position ->
                Toast.makeText(context, "Users $position", Toast.LENGTH_SHORT).show()
            })
        binding.homeRecyclerUsers.apply {
            addItemDecoration(UsersItemDecoration())
            adapter = usersAdapter
        }
    }

    private fun initRecyclerServices() {
        servicesAdapter = ServicesAdapter(onClick = { position ->
            Toast.makeText(context, "Services $position", Toast.LENGTH_SHORT).show()
        })
        binding.homeRecyclerSevice.apply {
            layoutManager = GridLayoutManager(context, 4)
            addItemDecoration(ServicesItemDecoration())
            adapter = servicesAdapter
        }
    }

    private fun setListeners() {
        binding.homeAddBalance.setOnClickListener {
            Toast.makeText(context, getString(R.string.roast_add), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        usersAdapter = null
        servicesAdapter = null
    }
}