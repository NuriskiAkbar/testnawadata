package com.example.pokedex

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ActivityDashboardBinding
import com.example.pokedex.retrofit.ApiEndpoint
import com.example.pokedex.retrofit.ApiService
import com.example.pokedex.retrofit.ResponseListApiPokemon
import com.example.pokedex.retrofit.ResultsItem
import com.example.pokedex.sqlite.DBHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDashboardBinding
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =  ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.rvListpokemon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewBinding.rvListpokemon.setHasFixedSize(true)
        getAllPokemon{ pokemon: MutableList<ResultsItem> ->
            val adapter = PokemonListAdapter(pokemon)
            viewBinding.rvListpokemon.adapter =  adapter
        }
    }

    private fun getAllMorePokemon(function: (MutableList<ResultsItem>) -> Unit) {
        count+= 20
        ApiService.getInstance().create(ApiEndpoint::class.java)
            .getAllMoreListPokemon(count, 20).enqueue(object : Callback<ResponseListApiPokemon>{
                override fun onResponse(
                    call: Call<ResponseListApiPokemon>,
                    response: Response<ResponseListApiPokemon>
                ) {
                    val data = response.body()?.results
                    return function( data as MutableList<ResultsItem>)
                }

                override fun onFailure(call: Call<ResponseListApiPokemon>, t: Throwable) {
                    Toast.makeText(this@DashboardActivity, "Ada error broooo", Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun getAllPokemon(function: (MutableList<ResultsItem>) -> Unit) {
        ApiService.getInstance().create(ApiEndpoint::class.java)
            .getAllListPokemon().enqueue(object : Callback<ResponseListApiPokemon>{
                override fun onResponse(
                    call: Call<ResponseListApiPokemon>,
                    response: Response<ResponseListApiPokemon>
                ) {
                    val data = response.body()?.results
                    return function( data as MutableList<ResultsItem>)
                }

                override fun onFailure(call: Call<ResponseListApiPokemon>, t: Throwable) {
                    Toast.makeText(this@DashboardActivity, "Ada error broooo", Toast.LENGTH_SHORT).show()
                }

            })
    }
}