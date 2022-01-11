package com.matrix.githubbrowser.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()
    private val addRepoViewModel: AddRepoViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        binding.repoNameDetailsTv.text = args.repoName
        binding.repoDescriptionDetailsTv.text = args.repoDescription

        addRepoViewModel.getRepo(args.repoOwner, args.repoName)

        addRepoViewModel.res.observe(activity!!, Observer {

            when(it.status){
                Status.SUCCESS -> {
                    it.let { res->
                        if (res?.status == Status.SUCCESS){
                            if (res.data?.openIssues != "0"){
                                binding.issuesBtn.text = "Issues (${res.data?.openIssues})"
                            }
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

        }

        binding.issuesBtn.setOnClickListener {

        }


    }
}