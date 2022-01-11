package com.matrix.githubbrowser.presentation.add

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.databinding.FragmentAddRepoBinding
import com.matrix.githubbrowser.domain.utils.Status
import com.matrix.githubbrowser.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRepoFragment : Fragment(R.layout.fragment_add_repo) {

    private var _binding: FragmentAddRepoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
//    private val args: AddRepoFrag by navArgs()
    private val addRepoViewModel: AddRepoViewModel by viewModels()

    private var currentItem: ItemsEntity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddRepoBinding.bind(view)

        binding.addButton.setOnClickListener {
            saveItem()
        }
    }

    private fun saveItem() {
        binding.apply {
            val repoOwner = repoOwnerEdit.text.toString()
            val repoName = repoNameEdit.text.toString()
            var repoDescription = "To_be_added"

            if (repoOwner.isEmpty() || repoName.isEmpty())
                return

            currentItem?.let {
                if (repoName == it.repoName && repoOwner == it.repoOwner) {
                    return
                }
            }

            addRepoViewModel.getRepo(repoOwner, repoName)

            addRepoViewModel.res.observe(activity!!, Observer {

                when(it.status){
                    Status.SUCCESS -> {
                        progress.visibility = View.GONE
                        it.let { res->
                            if (res?.status == Status.SUCCESS){
                                try {
                                    repoDescription = res.data?.description!!
                                } catch (e: NullPointerException) {
                                    repoDescription = "No description found."
                                }

                                Log.d("Logs", "saveItem: $repoOwner $repoDescription ${it.status}")
                                val newItem = ItemsEntity(repoName, repoOwner, repoDescription, 0)
                                viewModel.saveItem(newItem)
                                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()

                                val action = AddRepoFragmentDirections.actionAddRepoFragmentToMainFragment2()
                                findNavController().navigate(action)

                            }
                        }
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progress.visibility = View.GONE
                        Toast.makeText(requireContext(), "Invalid owner or repository name.", Toast.LENGTH_SHORT).show()
                    }
                }

            })

        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}