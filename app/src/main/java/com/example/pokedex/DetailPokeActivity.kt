package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ActivityDetailPokeBinding
import com.example.pokedex.retrofit.ApiEndpoint
import com.example.pokedex.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPokeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDetailPokeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailPokeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val data = intent.extras
        val url = data!!.getString("url")
        val no = data.getInt("no")

        ApiService.getInstance().create(ApiEndpoint::class.java)
            .getDetailPokemon(no).enqueue(object : Callback<ResponseDetailPokemon>{
                override fun onResponse(
                    call: Call<ResponseDetailPokemon>,
                    response: Response<ResponseDetailPokemon>
                ) {
                    val namaPoke = response.body()!!.name
                    viewBinding.tvNamapokemon.text = namaPoke
                    val imagePoke = response.body()!!.sprites!!.frontDefault
                    Glide.with(viewBinding.ivPokemon)
                        .load(imagePoke)
                        .into(viewBinding.ivPokemon)
                    val ability =  response.body()!!.abilities
                    viewBinding.rvAbilities.layoutManager = LinearLayoutManager(this@DetailPokeActivity, LinearLayoutManager.VERTICAL, false)
                    viewBinding.rvAbilities.setHasFixedSize(true)
                    val adapter = AbilitiesListAdapter(ability)
                    viewBinding.rvAbilities.adapter = adapter
                }

                override fun onFailure(call: Call<ResponseDetailPokemon>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

    }
}