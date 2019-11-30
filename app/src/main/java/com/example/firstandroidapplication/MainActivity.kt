package com.example.firstandroidapplication

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstandroidapplication.adapter.PostAdapter
import com.example.firstandroidapplication.client.Api
import com.example.firstandroidapplication.dto.Post
import io.ktor.client.request.get
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    private fun fetchData() = launch {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.bringToFront()

        val posts = withContext(Dispatchers.IO) {
            Api.client.get<MutableList<Post>>(Api.url)
        }
        with(recyclerView){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(posts)
        }
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }





}
