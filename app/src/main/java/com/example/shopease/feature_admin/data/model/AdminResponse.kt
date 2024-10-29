package com.example.shopease.feature_admin.data.model




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