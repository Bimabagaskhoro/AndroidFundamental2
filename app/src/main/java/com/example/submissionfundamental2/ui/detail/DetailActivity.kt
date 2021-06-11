package com.example.submissionfundamental2.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionfundamental2.databinding.ActivityDetailBinding
import com.example.submissionfundamental2.ui.adapter.SectionPagerAdapter
import kotlinx.coroutines.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailUserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        val actionbar = supportActionBar
        actionbar!!.title = username
        actionbar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
        }
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailActivity)
                        .load(it.avatar_url)
                        .apply(RequestOptions().override(55, 55))
                        .into(imgUserDetail)
                    tvName.text = it.name
                    tvBio.text = it.bio
                    tvLocation.text = it.location
                    tvCompany.text = it.company
                    tvFollowingNumber.text = "${it.followers}Following"
                    tvFollowersNumber.text = "${it.followers}Followers"
                }
            }
        })

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.toggleFav.isChecked = true
                        _isChecked = true
                    } else {
                        binding.toggleFav.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFav.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked) {
                viewModel.addFavorite(username.toString(), id, avatarUrl.toString())
                Toast.makeText(applicationContext, "Add to Favorite", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.removeFavorite(id)
                Toast.makeText(applicationContext, "Remove from Favorite", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.toggleFav.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabMode.setupWithViewPager(viewPager)
        }
    }
}

