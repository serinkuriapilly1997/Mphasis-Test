package com.app.mymainapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mymainapp.R
import com.app.mymainapp.databinding.ItemPeopleViewBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.utils.loadImage


class PeopleListAdapter(val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {

    inner class PeopleViewHolder(binding: ItemPeopleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    private val differCallback = object : DiffUtil.ItemCallback<PeopleResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PeopleResponseItem,
            newItem: PeopleResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PeopleResponseItem,
            newItem: PeopleResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<PeopleResponseItem>) {
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {

        val itemPeopleViewBinding: ItemPeopleViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_people_view, parent, false
        )
        return PeopleViewHolder(itemPeopleViewBinding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val data = differ.currentList[position]
        val binding = holder.itemBinding
        binding.item = data
        loadImage(binding.imageViewAvatar, data.avatar)
        binding.listner = onItemClickListener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}