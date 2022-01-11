package com.matrix.githubbrowser.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.databinding.FragmentAddRepoBinding
import com.matrix.githubbrowser.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)


        binding.repoNameDetailsTv.text = args.repoName
        binding.repoDescriptionDetailsTv.text = args.repoDescription

        binding.branchesBtn.setOnClickListener {

        }

        binding.issuesBtn.setOnClickListener {

        }


    }
}