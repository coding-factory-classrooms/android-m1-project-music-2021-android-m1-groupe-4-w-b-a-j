package com.trap.project_music.server.service

import com.trap.project_music.enums.VoteType
import com.trap.project_music.model.Vote
import com.trap.project_music.vo.PageJSON
import com.trap.project_music.vo.PostJSON
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIPost {
    @GET("api/data")
    fun posts(): Call<List<PostJSON>>

    @GET("api/posts/account/{accountId}")
    fun getPostsByAccount(@Path(value = "accountId") accountId: Long): Call<List<PostJSON>>

    @GET("api/posts/page/{page}")
    fun postsPage(@Path(value = "page") page: Int): Call<PageJSON>

    @POST("api/me/votes/{idPost}")
    fun vote(@Body choice: VoteType, @Path(value = "idPost") idPost: Long): Call<Vote>

    @Multipart
    @POST("api/posts/me")
    fun sendPosts(@Part("postRequest") postRequest: RequestBody, @Part leftImage: MultipartBody.Part, @Part rightImage: MultipartBody.Part): Call<PostJSON>
}