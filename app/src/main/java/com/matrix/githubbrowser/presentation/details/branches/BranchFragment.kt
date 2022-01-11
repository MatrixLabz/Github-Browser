package com.matrix.githubbrowser.presentation.details.branches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.databinding.FragmentBranchBinding
import com.matrix.githubbrowser.databinding.FragmentIssuesBinding
import com.matrix.githubbrowser.domain.utils.Status
import com.matrix.githubbrowser.presentation.adapters.BranchListAdapter
import com.matrix.githubbrowser.presentation.adapters.IssueListAdapter
import com.matrix.githubbrowser.presentation.details.issues.IssuesFragmentArgs
import com.matrix.githubbrowser.presentation.details.issues.IssuesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BranchFragment : Fragment(R.layout.fragment_branch) {

    private var _binding: FragmentBranchBinding? = null
    private val binding get() = _binding!!

    private val branchViewModel: BranchViewModel by viewModels()
    private val args: BranchFragmentArgs by navArgs()
    private val branchListAdapter = BranchListAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBranchBinding.bind(view)

        branchViewModel.getBranch(args.repoOwner, args.repoName)

        binding.rvBranches.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = branchListAdapter
        }

        branchViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    it.let { res->
                        branchListAdapter.setBranchList(res.data!!)
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })


    }

}