package com.matrix.githubbrowser.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.databinding.FragmentMainBinding
import com.matrix.githubbrowser.presentation.adapters.ItemsListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val TAG = "Logs"
    private val viewModel: MainViewModel by viewModels()

    private lateinit var itemsListAdapter: ItemsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)
        setHasOptionsMenu(true)

        setupRecyclerView()
        viewModel.itemsEntities.observe(viewLifecycleOwner, Observer {

            if (it.isEmpty()) {
                binding.rvItems.visibility = View.GONE
                binding.emptyLinearLayout.visibility = View.VISIBLE
            } else {
                binding.emptyLinearLayout.visibility = View.GONE
                binding.rvItems.visibility = View.VISIBLE
            }

            itemsListAdapter.submitList(it)
        })


        binding.addRepoBtn2.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddRepoFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() {
        itemsListAdapter = ItemsListAdapter()

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemsListAdapter
        }

        itemsListAdapter.setOnItemClickListener {
            // Add logic for clicking a repository

//            val action = MainFragmentDirections.actionMainFragmentToAddRepoFragment()
//            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // For adding menu to fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.addRepoBtn1 -> {
                val action = MainFragmentDirections.actionMainFragmentToAddRepoFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}