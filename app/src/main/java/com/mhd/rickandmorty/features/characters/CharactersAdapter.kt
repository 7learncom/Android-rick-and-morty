package com.mhd.rickandmorty.features.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.mhd.rickandmorty.R
import com.mhd.rickandmorty.data.network.response.CharactersPagingResponse
import com.mhd.rickandmorty.databinding.ItemCharacterBinding


class CharactersAdapter :
    PagingDataAdapter<CharactersPagingResponse.Character, CharactersAdapter.CharacterViewHolder>(
        UserComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharactersPagingResponse.Character) {
            binding.ivImage.load(character.image)
            binding.tvName.text = character.name
            binding.tvOrigin.text = binding.root.context.getString(
                R.string.placeHolder_characterOrigin,
                character.origin.name
            )

            val statusResId = when (character.status) {
                "Alive" -> R.drawable.ic_alive
                "Dead" -> R.drawable.ic_dead
                "unknown" -> R.drawable.ic_unknown
                else -> throw IllegalStateException("Status ${character.status} is not valid")
            }
            binding.viewStatus.setBackgroundResource(statusResId)
        }
    }
}

object UserComparator : DiffUtil.ItemCallback<CharactersPagingResponse.Character>() {
    override fun areItemsTheSame(
        oldItem: CharactersPagingResponse.Character,
        newItem: CharactersPagingResponse.Character
    ): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharactersPagingResponse.Character,
        newItem: CharactersPagingResponse.Character
    ): Boolean {
        return oldItem == newItem
    }
}