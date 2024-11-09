package com.example.shopease.feature_admin.data.remote

import com.example.shopease.feature_admin.data.model.BinLookUpList
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface NeutrinoService {

    @POST("/bin-lookup")
    suspend fun binlookUp(@Query("bin-number") binNumber:Int) : Response<BinLookUpList>
}