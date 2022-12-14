package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import com.example.githubusersearch.GitHubRepo as GitHubRepo

class GitHubUserRepositoryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_user_repository_list)

        val id = intent.getStringExtra("userid")!!
        Log.d("mytag",id)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(GitHubAPIService::class.java)
        val apiCallForData = apiService.getRepos(id,
            "token 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")
        apiCallForData.enqueue(object: Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>> ) {
                val data = response.body()!!
                Log.d("mytag", data.toString())
                val list = findViewById<RecyclerView>(R.id.repo_list)
                list.layoutManager = LinearLayoutManager(this@GitHubUserRepositoryListActivity)
                list.adapter = GitHubRepoListAdapter(data)
                list.setHasFixedSize(false)
            }

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {

            }
        })
    }
}
