package com.example.sushiveslatestapp.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sushiveslatestapp.databinding.FragmentHomeBinding
import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.domain.entitys.home.Users
import com.example.sushiveslatestapp.presentation.home.services.UsersAdapter
import com.example.sushiveslatestapp.presentation.home.services.UsersItemDecoration

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    //private var fragmentInteractor: FragmentListNewsInteractor? = null
    private var usersAdapter: UsersAdapter? = null
    private var servicesAdapter: ServicesAdapter? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setListeners()
        // setObservers()
        initRecyclerUsers()
        // initListNews()
        initRecyclerServices()
    }

    private fun initRecyclerUsers() {
        usersAdapter = UsersAdapter(
            listOf(
                Users("Dahsa", "https://pbs.twimg.com/media/ETnk8TzUcAA3Ohj.jpg"),
                Users("Flag", ""),
                Users("Flag", ""),
                Users("Alena", "")
            )
        )
        binding.homeRecyclerUsers.apply {
            addItemDecoration(UsersItemDecoration())
            adapter = usersAdapter
        }
    }

    private fun initRecyclerServices() {
        servicesAdapter = ServicesAdapter(
            listOf(
                Services("Send Money", ""),
                Services("Send Money", ""),
                Services("Send Money", ""),
                Services("Send Money", ""),
                Services("Cashback Offer", ""),
                Services("Movie Tickets", ""),
                Services("Flight Tickets", ""),
                Services("More Options", "")
            )
        )
        binding.homeRecyclerSevice.apply {
            layoutManager = GridLayoutManager(context, 4)
            addItemDecoration(ServicesItemDecoration())
            adapter = servicesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        usersAdapter = null
        servicesAdapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        //fragmentInteractor = null
    }
}