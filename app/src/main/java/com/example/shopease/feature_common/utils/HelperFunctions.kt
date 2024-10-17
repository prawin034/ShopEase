package com.example.shopease.feature_common.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import java.net.URL


fun mapIconName(item: String): ImageVector {

    return when (item.lowercase()) {
        "home" -> Icons.Default.Home
        "cart" -> Icons.Default.ShoppingCart
        "search" -> Icons.Default.Search
        "profile" -> Icons.Default.Person
        else -> Icons.Default.Bookmarks
    }

}



fun getBitMap(imageUrl:String) :Bitmap {
    val bitMap = URL(imageUrl).openStream().use {
        BitmapFactory.decodeStream(it)
    }
    return  bitMap
}


fun productCategoryUrl(slug: String?): String {

    return when (slug?.lowercase()) {
        "beauty" -> "https://cdn.dummyjson.com/products/images/beauty/Red%20Nail%20Polish/thumbnail.png"
        "fragrances" -> "https://cdn.dummyjson.com/products/images/fragrances/Chanel%20Coco%20Noir%20Eau%20De/thumbnail.png"
        "furniture" -> "https://cdn.dummyjson.com/products/images/furniture/Annibale%20Colombo%20Sofa/thumbnail.png"
        "groceries" -> "https://cdn.dummyjson.com/products/images/groceries/Cat%20Food/thumbnail.png"
        "home-decoration" ->  "https://cdn.dummyjson.com/products/images/home-decoration/Decoration%20Swing/thumbnail.png"
        "kitchen-accessories" -> "https://cdn.dummyjson.com/products/images/kitchen-accessories/Carbon%20Steel%20Wok/thumbnail.png"
        "laptops" -> "https://cdn.dummyjson.com/products/images/laptops/Apple%20MacBook%20Pro%2014%20Inch%20Space%20Grey/thumbnail.png"
        "mens-shirts" -> "https://cdn.dummyjson.com/products/images/mens-shirts/Man%20Plaid%20Shirt/thumbnail.png"
        "mens-shoes" -> "https://cdn.dummyjson.com/products/images/mens-shoes/Nike%20Air%20Jordan%201%20Red%20And%20Black/thumbnail.png"
        "mens-watches" -> "https://cdn.dummyjson.com/products/images/mens-watches/Brown%20Leather%20Belt%20Watch/thumbnail.png"
        "mobile-accessories" -> "https://cdn.dummyjson.com/products/images/mobile-accessories/Apple%20Airpods/thumbnail.png"
        "motorcycle" -> "https://cdn.dummyjson.com/products/images/motorcycle/Generic%20Motorcycle/thumbnail.png"
        "skin-care" -> "https://cdn.dummyjson.com/products/images/skin-care/Olay%20Ultra%20Moisture%20Shea%20Butter%20Body%20Wash/thumbnail.png"
        "smartphones" -> "https://cdn.dummyjson.com/products/images/smartphones/iPhone%20X/thumbnail.png"
        "sports-accessories" -> "https://cdn.dummyjson.com/products/images/sports-accessories/Cricket%20Bat/thumbnail.png"
        "sunglasses" -> "https://cdn.dummyjson.com/products/images/sunglasses/Green%20and%20Black%20Glasses/thumbnail.png"
        "tablets" -> "https://cdn.dummyjson.com/products/images/tablets/iPad%20Mini%202021%20Starlight/thumbnail.png"
        "tops" -> "https://cdn.dummyjson.com/products/images/tops/Blue%20Frock/thumbnail.png"
        "vehicle" -> "https://cdn.dummyjson.com/products/images/vehicle/Durango%20SXT%20RWD/thumbnail.png"
        "womens-bags" -> "https://cdn.dummyjson.com/products/images/womens-bags/Blue%20Women's%20Handbag/thumbnail.png"
        "womens-dresses" -> "https://cdn.dummyjson.com/products/images/womens-dresses/Corset%20Leather%20With%20Skirt/thumbnail.png"
        "womens-jewellery" -> "https://cdn.dummyjson.com/products/images/womens-jewellery/Green%20Crystal%20Earring/thumbnail.png"
        "womens-shoes" -> "https://cdn.dummyjson.com/products/images/womens-shoes/Black%20&%20Brown%20Slipper/thumbnail.png"
        "womens-watches" -> "https://cdn.dummyjson.com/products/images/womens-watches/Rolex%20Cellini%20Moonphase/thumbnail.png"
        else -> ""
    }

}




@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomColor() :androidx.compose.ui.graphics.Color {

    val random = kotlin.random.Random

    // Generate random ARGB values
    val alpha = random.nextInt(256) // 0 to 255 (for transparency)
    val red = random.nextInt(256) // 0 to 255
    val green = random.nextInt(256) // 0 to 255
    val blue = random.nextInt(256) // 0 to 255


    val argbColor = Color.argb(alpha, red, green, blue)

    return  androidx.compose.ui.graphics.Color(argbColor)

}