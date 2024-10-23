package com.example.shopease.feature_admin.data.model





data class AddToCartRequest(
   val userId : Int,
   val products:List<AddToCarProduct>
)

data  class AddToCarProduct(
   val id :Int,
   val quantity :Int
)
