package com.example.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemlistabilitiesBinding

class AbilitiesListAdapter(
    private val abilities: List<AbilitiesItem?>?
): RecyclerView.Adapter<AbilitiesListAdapter.AbilitiesListViewHolder>(){

    class AbilitiesListViewHolder(private val binding: ItemlistabilitiesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(abilitiesItem: AbilitiesItem) {
            binding.tvAbilitiespokemon.text = abilitiesItem.ability!!.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesListViewHolder {
        return AbilitiesListViewHolder(
            ItemlistabilitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return abilities!!.size
    }

    override fun onBindViewHolder(holder: AbilitiesListViewHolder, position: Int) {
        abilities?.get(position)?.let { holder.bind(it) }
    }


}