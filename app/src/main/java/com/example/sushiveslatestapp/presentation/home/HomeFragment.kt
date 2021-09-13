package com.example.sushiveslatestapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sushiveslatestapp.databinding.FragmentHomeBinding
import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import com.example.sushiveslatestapp.presentation.home.services.UsersAdapter
import com.example.sushiveslatestapp.presentation.home.services.UsersItemDecoration

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    private var usersAdapter: UsersAdapter? = null
    private var servicesAdapter: ServicesAdapter? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        // initListNews()
    }

    private fun setObservers() {
        viewModel.balance.observe(viewLifecycleOwner) { balance ->
            binding.homeBalance.text = balance.toString()
        }
        viewModel.listUsers.observe(viewLifecycleOwner) { listUsers ->
            usersAdapter?.setListUsers(listUsers)
        }
        viewModel.listServices.observe(viewLifecycleOwner) { listService ->
            servicesAdapter?.setListUsers(listService)
        }
    }

    private fun initRecyclerUsers() {
        usersAdapter = UsersAdapter(onClickButton =
        { Toast.makeText(context, "Add Users", Toast.LENGTH_SHORT).show() },
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
            Toast.makeText(context, "AddBalance", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        usersAdapter = null
        servicesAdapter = null
    }
}