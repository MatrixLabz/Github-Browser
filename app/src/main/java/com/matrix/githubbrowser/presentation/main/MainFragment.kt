package com.matrix.githubbrowser.presentation.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeItem(itemsListAdapter.getItemAt(viewHolder.adapterPosition))
                Toast.makeText(requireContext(), "Repository deleted.", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.rvItems)


        binding.addRepoBtn2.setOnClickListener {

            if (checkInternet(requireContext())) {
                val action = MainFragmentDirections.actionMainFragmentToAddRepoFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            }
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
            if (checkInternet(requireContext())) {
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "No Internet Connection.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }


    // For adding menu to fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addRepoBtn1 -> {
                if (checkInternet(requireContext())) {
                    val action = MainFragmentDirections.actionMainFragmentToAddRepoFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}