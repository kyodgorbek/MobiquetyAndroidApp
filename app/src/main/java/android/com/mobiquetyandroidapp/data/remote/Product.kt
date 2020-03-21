package android.com.mobiquetyandroidapp.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        @SerializedName("id") val id: String,
        @SerializedName("categoryId") val categoryId: String,
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String,
        @SerializedName("salePrice") val salePrice: SalePrice
) : Parcelable