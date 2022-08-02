package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.lang.reflect.Type

// https://api.github.com/users/chojangg
interface GitHubAPIService {
    @GET("/users/{userId}")
    fun getUser(
        @Path("userId") id: String,
        @Header("Authorization") pat: String
    ) : Call<GitHubUser>

    @GET("/users/{userId}/repos")
    fun getRepos(
        @Path("userId") id: String,
        @Header("Authorization") pat: String
    ) : Call<List<GitHubRepo>>

}

data class GitHubUser(
    val id: Int,
    val login: String,
    val name: String?,
    val following: Int,
    val followers: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String
    )

data class GitHubRepo(
    val name: String,
    val html_url: String,
    val description: String?,
    val forks_count: Int,
    val watchers_count: Int,
    val stargazers: Int
)

/*
class GitHubUserDeserializer : JsonDeserializer<GitHubUser> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubUser {
        val root = json?.getAsJsonObject()
        val id = root?.getAsJsonPrimitive("id")?.asInt
        val login = root?.getAsJsonPrimitive("login")?.asString

        return GitHubUser(id!!, login!!)
    }

}
 */
