package com.mhd.rickandmorty.features.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mhd.rickandmorty.data.network.response.EpisodesPagingResponse
import com.mhd.rickandmorty.databinding.ItemEpisodeBinding

class EpisodesAdapter :
    PagingDataAdapter<EpisodesPagingResponse.Episode, EpisodesAdapter.EpisodeViewHolder>(
        EpisodeComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodeBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: EpisodesPagingResponse.Episode) {
            binding.tvName.text = episode.name
            binding.tvAirDate.text = episode.airDate
            binding.tvSeasonEpisode.text = episode.episode
        }
    }

}

object EpisodeComparator : DiffUtil.ItemCallback<EpisodesPagingResponse.Episode>() {
    override fun areItemsTheSame(
        oldItem: EpisodesPagingResponse.Episode,
        newItem: EpisodesPagingResponse.Episode
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EpisodesPagingResponse.Episode,
        newItem: EpisodesPagingResponse.Episode
    ): Boolean {
        return oldItem == newItem
    }

}