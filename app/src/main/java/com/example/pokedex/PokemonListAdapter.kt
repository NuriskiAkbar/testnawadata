package com.example.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemlistBinding
import com.example.pokedex.retrofit.ResultsItem

class PokemonListAdapter(
    private val pokemon: List<ResultsItem>
): RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>(), Filterable {

    internal var filteredPoke : List<ResultsItem> = pokemon

    class PokemonListViewHolder(private val binding: ItemlistBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(resultsItem: ResultsItem) {
            binding.tvNamapokemon.text = resultsItem.name

            binding.tvNamapokemon.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("",resultsItem.name)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(
            ItemlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filteredPoke.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(filteredPoke[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charString: CharSequence?): FilterResults {
                val charSearch = charString.toString()
                if (charSearch.isEmpty()) {
                    filteredPoke = pokemon
                } else {
                    val resultList = ArrayList<ResultsItem>()
                    for (row in pokemon){
                        if (row.name!!.toLowerCase().contains(charSearch.lowercase()))
                            resultList.add(row)
                    }
                    filteredPoke = resultList
                }
                val filterResult = Filter.FilterResults()
                filterResult.values = filteredPoke
                return filterResult
            }

            override fun publishResults(charString: CharSequence?, results: FilterResults?) {
                filteredPoke = results!!.values as List<ResultsItem>
                notifyDataSetChanged()
            }

        }
    }

}