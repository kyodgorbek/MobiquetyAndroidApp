package android.com.mobiquetyandroidapp.data.remote

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("products") val products: List<Product>
)