package com.mhd.rickandmorty.features.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mhd.rickandmorty.data.network.response.LocationsPagingResponse
import com.mhd.rickandmorty.databinding.ItemLocationBinding

class LocationsAdapter :
    PagingDataAdapter<LocationsPagingResponse.Location, LocationsAdapter.LocationViewHolder>(
        LocationComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationsPagingResponse.Location) {
            binding.tvName.text = item.name
        }
    }

}

object LocationComparator : DiffUtil.ItemCallback<LocationsPagingResponse.Location>() {
    override fun areItemsTheSame(
        oldItem: LocationsPagingResponse.Location,
        newItem: LocationsPagingResponse.Location
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: LocationsPagingResponse.Location,
        newItem: LocationsPagingResponse.Location
    ): Boolean {
        return oldItem == newItem
    }

}