package com.matrix.githubbrowser.presentation.details.issues

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.data.models.api.GetIssueSubModel
import com.matrix.githubbrowser.databinding.FragmentDetailsBinding
import com.matrix.githubbrowser.databinding.FragmentIssuesBinding
import com.matrix.githubbrowser.domain.utils.Status
import com.matrix.githubbrowser.presentation.adapters.IssueListAdapter
import com.matrix.githubbrowser.presentation.add.AddRepoViewModel
import com.matrix.githubbrowser.presentation.details.DetailsFragmentArgs
import com.matrix.githubbrowser.presentation.details.DetailsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IssuesFragment : Fragment(R.layout.fragment_issues) {

    private var _binding: FragmentIssuesBinding? = null
    private val binding get() = _binding!!

    private val issuesViewModel: IssuesViewModel by viewModels()
    private val args: IssuesFragmentArgs by navArgs()
    private val issueListAdapter = IssueListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIssuesBinding.bind(view)

        issuesViewModel.getIssue(args.repoOwner, args.repoName)


        binding.rvIssues.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = issueListAdapter
        }

        issuesViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    it.let { res->
                        issueListAdapter.setIssueList(res.data!!)
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