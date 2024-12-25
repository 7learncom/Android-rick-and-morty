package com.mhd.rickandmorty.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mhd.rickandmorty.R
import com.mhd.rickandmorty.databinding.ItemLoadingBinding

class CommonLoadStateAdapter(
    private val onRetryClick: () -> Unit
) : LoadStateAdapter<CommonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_loading, parent, false)
        return LoadStateViewHolder(view, onRetryClick)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    class LoadStateViewHolder(itemView: View, onRetryClick: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLoadingBinding.bind(itemView)
            .also {
                it.btnRetry.setOnClickListener { onRetryClick() }
            }

        fun bind(loadState: LoadState) {
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.tvErrorMessage.isVisible = loadState is LoadState.Error
            binding.progressCircular.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                binding.tvErrorMessage.text = loadState.error.message
            }
        }
    }
}