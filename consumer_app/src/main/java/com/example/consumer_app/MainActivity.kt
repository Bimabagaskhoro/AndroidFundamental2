package com.example.consumer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumer_app.adapter.UserAdapter
import com.example.consumer_app.databinding.ActivityMainBinding
import com.example.consumer_app.viewmodel.FavoriteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFavorite.setHasFixedSize(true)
            rvFavorite.layoutManager = LinearLayoutManager(this@MainActivity)
            rvFavorite.adapter = adapter
        }

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.setFavoriteUser(this)
        viewModel.getFavoriteUser().observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })
    }
}