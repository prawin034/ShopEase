package com.example.shopease.feature_admin.data.model

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import java.time.YearMonth
import java.util.UUID


data class AllProductCategory(
    val slug : String?,
    val name : String?,
    val url : String?
)



data class ProductCategory(
    val products: List<Product>
)


data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String?,
    val sku: String,
    val weight: Int,
    val dimensions: Dimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Review>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val meta: Meta,
    val images: List<String>,
    val thumbnail: String
)



data class SearchProducts(
    var products: List<Product>
)

data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)


data class ProductPager(
    val id: Int,
    val url: String,
)


data class AddToCartResponse(
    val id :Int,
    val products: List<CartResponseList>,
    val total : Double,
    val discountedTotal : Double,
    val userId : Int,
    val totalProducts : Int,
    val totalQuantity : Int

)





data class CartResponseList(
    val id : Int,
    val title :String,
    val price : Double,
    var quantity : Int,
    val total : Double,
    val discountPercentage :Double,
    val discountedTotal : Double,
    val thumbnail :String,
)






/* Card
 */


data class CardList(
    val id : String = UUID.randomUUID().toString(),
    val cardNumber : String?,
    val cardHolderName :String?,
    val expiryDate :String?,
    val cvv :String?,
    val cardColor : Color = Color.Black

)


data class BinLookUpList(
    @SerializedName("bin-number") val binNumber: String,
    @SerializedName("card-brand") val cardBrand: String,
    @SerializedName("card-category") val cardCategory: String,
    @SerializedName("card-type") val cardType: String,
    @SerializedName("country") val country: String,
    @SerializedName("country-code") val countryCode: String,
    @SerializedName("country-code3") val countryCode3: String,
    @SerializedName("currency-code") val currencyCode: String,
    @SerializedName("ip-blocklisted") val ipBlocklisted: Boolean,
    @SerializedName("ip-blocklists") val ipBlocklists: List<String>,
    @SerializedName("ip-city") val ipCity: String?,
    @SerializedName("ip-country") val ipCountry: String?,
    @SerializedName("ip-country-code") val ipCountryCode: String?,
    @SerializedName("ip-country-code3") val ipCountryCode3: String?,
    @SerializedName("ip-matches-bin") val ipMatchesBin: Boolean,
    @SerializedName("ip-region") val ipRegion: String?,
    @SerializedName("is-commercial") val isCommercial: Boolean,
    @SerializedName("is-prepaid") val isPrepaid: Boolean,
    @SerializedName("issuer") val issuer: String,
    @SerializedName("issuer-phone") val issuerPhone: String?,
    @SerializedName("issuer-website") val issuerWebsite: String?,
    @SerializedName("valid") val valid: Boolean
)