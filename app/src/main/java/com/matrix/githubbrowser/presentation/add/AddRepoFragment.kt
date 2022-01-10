package com.matrix.githubbrowser.presentation.add

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.matrix.githubbrowser.R
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.databinding.FragmentAddRepoBinding
import com.matrix.githubbrowser.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRepoFragment : Fragment(R.layout.fragment_add_repo) {

    private var _binding: FragmentAddRepoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
//    private val args: AddRepoFrag by navArgs()

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
            val repoDescription = "To_be_added"

            Log.d("Logs", "saveItem: $repoOwner")

            if (repoOwner.isEmpty() || repoName.isEmpty())
                return

            currentItem?.let {
                if (repoName == it.repoName && repoOwner == it.repoOwner) {
                    return
                }
            }

            val newItem = ItemsEntity(repoName, repoOwner, repoDescription, 0)

            if (newItem.repoName != null && newItem.repoOwner != null) {

                Log.d("Logs", "saveItem: ${newItem.id} ${newItem.repoDescription}")
                viewModel.saveItem(newItem)
                Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()

                val action = AddRepoFragmentDirections.actionAddRepoFragmentToMainFragment2()
                findNavController().navigate(action)
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}