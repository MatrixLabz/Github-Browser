package com.matrix.githubbrowser.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.databinding.FragmentDetailsBinding
import com.matrix.githubbrowser.domain.utils.Status
import com.matrix.githubbrowser.presentation.add.AddRepoViewModel
import com.matrix.githubbrowser.presentation.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import com.matrix.githubbrowser.presentation.main.MainViewModel
import android.content.DialogInterface

import com.matrix.githubbrowser.presentation.MainActivity

import com.google.android.material.dialog.MaterialAlertDialogBuilder





@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private val addRepoViewModel: AddRepoViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels()
    private var countIssue = "0"

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        setHasOptionsMenu(true)

        binding.repoNameDetailsTv.text = args.itemsEntity.repoName
        binding.repoDescriptionDetailsTv.text = args.itemsEntity.repoDescription

        addRepoViewModel.getRepo(args.itemsEntity.repoOwner, args.itemsEntity.repoName)

        addRepoViewModel.res.observe(activity!!, Observer {

            when(it.status){
                Status.SUCCESS -> {
                    it.let { res->
                        if (res?.status == Status.SUCCESS){
                            if (res.data?.openIssues != "0"){
                                binding.issuesBtn.text = "Issues (${res.data?.openIssues})"
                            }
                            countIssue = res.data?.openIssues.toString()
                            Log.d("Issues", "Issues ${res.data?.openIssues}")
                        }
                    }
                }
                Status.LOADING -> {
                    Log.d("Issues", "Loading.....")
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Not connected to internet. Please connect to internet first.", Toast.LENGTH_LONG).show()
                    val action = DetailsFragmentDirections.actionDetailsFragmentToMainFragment()
                    findNavController().navigate(action)
                }
            }

        })

        binding.branchesBtn.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToBranchFragment(args.itemsEntity.repoName, args.itemsEntity.repoOwner)
            findNavController().navigate(action)
        }

        binding.issuesBtn.setOnClickListener {
            if (countIssue != "0") {
                val action = DetailsFragmentDirections.actionDetailsFragmentToIssuesFragment(
                    args.itemsEntity.repoName,
                    args.itemsEntity.repoOwner
                )
                findNavController().navigate(action)
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("No issues found.")
                    .setMessage("There are no issues! Add a repo which has issues.")
                    .setNegativeButton(
                        "Cancel"
                    ) { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                    .show()
            }
        }

    }


    // For adding menu to fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.deleteRepo -> {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Repository")
                    .setMessage("Are you sure, you want to delete?.")
                    .setPositiveButton(
                        "Yes"
                    ) { dialogInterface, i ->
                        viewModel.removeItem(args.itemsEntity)
                        val action = DetailsFragmentDirections.actionDetailsFragmentToMainFragment()
                        findNavController().navigate(action)
                    }
                    .setNegativeButton(
                        "Cancel"
                    ) { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                    .show()
            }

            R.id.viewOnWeb -> {
                val url = "https://github.com/${args.itemsEntity.repoOwner}/${args.itemsEntity.repoName}"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}