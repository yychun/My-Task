package com.yychun1217.mychallenge

import com.yychun1217.mychallenge.model.remote.DeliveryData
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryService {
    @GET("/v2/deliveries")
    suspend fun deliveries(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GetDeliveriesResponse?
}

typealias GetDeliveriesResponse = List<DeliveryData>