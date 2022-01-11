package com.matrix.githubbrowser.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matrix.githubbrowser.data.models.ItemsEntity
import com.matrix.githubbrowser.databinding.ListItemBinding
import com.matrix.githubbrowser.presentation.main.MainFragment
import com.matrix.githubbrowser.presentation.main.MainFragmentDirections

class ItemsListAdapter: ListAdapter<ItemsEntity, ItemsListAdapter.ItemsViewHolder>(DiffCallBack()) {

    inner class ItemsViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(getItem(adapterPosition))
                }
            }
        }

        fun bind(currentItem: ItemsEntity) {
            binding.apply {
                repoNameTv.text = currentItem.repoName
                repoDescriptionTv.text = currentItem.repoDescription
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val currentNode = getItem(position)

        if (currentNode != null) {
            holder.bind(currentNode)
        }
    }

    private var onItemClickListener: ((ItemsEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (ItemsEntity) -> Unit) {
        onItemClickListener= listener
    }

    fun getItemAt(position: Int): ItemsEntity = getItem(position)

    class DiffCallBack: DiffUtil.ItemCallback<ItemsEntity>() {
        override fun areItemsTheSame(oldItem: ItemsEntity, newItem: ItemsEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemsEntity, newItem: ItemsEntity): Boolean {
            return oldItem == newItem
        }

    }

}