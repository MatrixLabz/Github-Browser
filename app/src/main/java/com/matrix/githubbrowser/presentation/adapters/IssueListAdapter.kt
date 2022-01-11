package com.matrix.githubbrowser.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.githubbrowser.data.models.api.GetIssuesModel
import com.matrix.githubbrowser.databinding.ListIssueBinding

class IssueListAdapter: RecyclerView.Adapter<IssueListAdapter.MainViewHolder>() {

    var issues = mutableListOf<GetIssuesModel>()

    fun setIssueList(issues: MutableList<GetIssuesModel>) {
        this.issues = issues.toMutableList()
        notifyDataSetChanged()
    }

    inner class MainViewHolder(val binding: ListIssueBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListIssueBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val issue = issues[position]

        Log.d("TAG", "onViewCreated: ${issue.title}")
        holder.binding.issueTitleTv.text = issue.title
        holder.binding.loginTv.text = issue.setGetIssueSubModel.login
        Glide.with(holder.itemView.context).load(issue.setGetIssueSubModel.avatarUrl).into(holder.binding.profileImage)

    }

    override fun getItemCount(): Int {
        return issues.size
    }
}

