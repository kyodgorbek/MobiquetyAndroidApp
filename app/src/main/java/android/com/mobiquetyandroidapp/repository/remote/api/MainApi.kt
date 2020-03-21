package android.com.mobiquetyandroidapp.repository.remote.api

import android.com.mobiquetyandroidapp.data.remote.Category
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MainApi {

    @GET("/")
    fun productListAsync(): Deferred<List<Category>>
}