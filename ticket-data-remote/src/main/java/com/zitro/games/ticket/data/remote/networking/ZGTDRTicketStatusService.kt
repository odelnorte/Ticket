package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketStatusApiModel
import com.zitro.games.util.common.ZGU_API_MACHINE_COMPONENT_STATUS
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZGTDRTicketStatusService {

    @GET(ZGU_API_MACHINE_COMPONENT_STATUS)
    suspend fun status(
        @Header("Authorization") token: String,
        @Query("tarea") task : String
    ) : ZGCDResponse<List<ZGTDRTicketStatusApiModel>>

}