package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRoomsApiModel
import com.zitro.games.util.common.ZGU_API_MACHINE_COMPONENT_ROOMS
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZGTDRTicketRoomsService {

    @GET(ZGU_API_MACHINE_COMPONENT_ROOMS)
    suspend fun rooms(
        @Header("Authorization") token: String,
        @Query("regionId") task : Int
    ) : ZGCDResponse<List<ZGTDRTicketRoomsApiModel>>
}