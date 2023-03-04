package com.app.mymainapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mymainapp.R
import com.app.mymainapp.databinding.ItemPeopleViewBinding
import com.app.mymainapp.databinding.ItemRoomsViewBinding
import com.app.mymainapp.listeners.OnItemClickListener
import com.app.mymainapp.models.PeopleResponseItem
import com.app.mymainapp.models.RoomsListResponse
import com.app.mymainapp.utils.loadImage


class RoomsAdapter(val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RoomsAdapter.PeopleViewHolder>() {

    inner class PeopleViewHolder(binding: ItemRoomsViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    private val differCallback = object : DiffUtil.ItemCallback<RoomsListResponse>() {
        override fun areItemsTheSame(
            oldItem: RoomsListResponse,
            newItem: RoomsListResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RoomsListResponse,
            newItem: RoomsListResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<RoomsListResponse>) {
        differ.submitList(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {

        val itemRoomsViewBinding: ItemRoomsViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_rooms_view, parent, false
        )
        return PeopleViewHolder(itemRoomsViewBinding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val data = differ.currentList[position]
        val binding = holder.itemBinding
        binding.item = data
        if (data.isOccupied == true) {

            binding.layoutRooms.setBackgroundResource(R.drawable.background_occupied)

        } else {

            binding.layoutRooms.setBackgroundResource(R.drawable.background_unoccupied)
        }
        binding.listner = onItemClickListener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}