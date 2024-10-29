package com.example.shopease.feature_admin.data.model





data class AddToCartRequest(
   val userId : Int,
   val products:List<AddToCarProduct>
)

data  class AddToCarProduct(
   val id :Int,
   val quantity :Int,
   val stock :Int,
   val minimumOrderQuantity : Int
)



data class UpdateCartRequest(
   val merge :Boolean = true,
   val products: List<AddToCarProduct>
)