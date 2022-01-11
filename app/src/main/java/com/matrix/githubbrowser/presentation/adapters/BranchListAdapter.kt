package com.matrix.githubbrowser.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matrix.githubbrowser.data.models.api.GetBranchesModel
import com.matrix.githubbrowser.databinding.ListBranchBinding

class BranchListAdapter: RecyclerView.Adapter<BranchListAdapter.MainViewHolder>() {

    var branches = mutableListOf<GetBranchesModel>()

    fun setBranchList(branches: MutableList<GetBranchesModel>) {
        this.branches = branches.toMutableList()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: ListBranchBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListBranchBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val branch = branches[position]

        Log.d("TAG", "onViewCreated: ${branch.branchName}")
        holder.binding.branchNameTv.text = branch.branchName

    }

    override fun getItemCount(): Int {
        return branches.size
    }
}

