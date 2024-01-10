package com.example.myapplication232

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication232.databinding.ItemCharacterBinding

class CharacterAdapter :
    PagingDataAdapter<CharacterItemUiState, CharacterAdapter.CharacterItemViewHolder>(COUNTRY_DIFFER) {

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterItemViewHolder(binding)
    }

    inner class CharacterItemViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun clearData() {
            binding.characterName.text = ""
            binding.gender.text = ""
            binding.dob.text = ""
        }

        fun bindData(position: Int) {
            clearData()

            getItem(position)?.let { item ->
                binding.characterName.text = item.name
                binding.gender.text = item.gender
                binding.dob.text = item.birthYear
            }

        }

    }
}

val COUNTRY_DIFFER = object : DiffUtil.ItemCallback<CharacterItemUiState>() {
    override fun areItemsTheSame(oldItem: CharacterItemUiState, newItem: CharacterItemUiState) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: CharacterItemUiState,
        newItem: CharacterItemUiState
    ): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.gender == newItem.gender &&
                oldItem.birthYear == newItem.birthYear
    }
}