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
