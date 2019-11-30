package com.example.firstandroidapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstandroidapplication.adapter.PostAdapter
import com.example.firstandroidapplication.client.Api
import com.example.firstandroidapplication.dto.Post
import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()


    }

    private fun fetchData() = launch {
        // UI - Progress Bar
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val posts = withContext(Dispatchers.IO) {
            Api.client.get<MutableList<Post>>(Api.url)
        }
        with(recyclerView){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(posts)

        }
        // Hide ProgressBar
//        Toast.makeText(this@MainActivity, "length of list ${posts.size}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }





}
