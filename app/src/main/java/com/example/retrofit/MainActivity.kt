package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.UserAdapter
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.NewsDTO
import com.example.retrofit.model.User
import com.example.retrofit.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userAdapter by lazy { UserAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
        callNewsApi()
    }

    private fun callNewsApi() {
        val retrofit = RetroInstance.retroInstance()
        retrofit.getAllNews("us").enqueue(object : Callback<NewsDTO> {
            override fun onResponse(call: Call<NewsDTO>, response: Response<NewsDTO>) {
                if (response.isSuccessful) {
                    Log.d("@@@", response.body().toString())
                }
            }

            override fun onFailure(call: Call<NewsDTO>, t: Throwable) {
                Log.d("@@@", "${t.message}")
            }
        })
    }

    private fun callGithubApi() {
        val retrofit = RetroInstance.retroInstance()
        retrofit.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    userAdapter.submitList(response.body())
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("@@@", "${t.message}")
            }
        })
    }
}