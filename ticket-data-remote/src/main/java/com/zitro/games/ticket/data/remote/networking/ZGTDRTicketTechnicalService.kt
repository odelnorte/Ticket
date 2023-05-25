package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketTechnicalApiModel
import com.zitro.games.util.common.ZGU_API_TICKET_TECHNICAL
import retrofit2.http.GET
import retrofit2.http.Header

interface ZGTDRTicketTechnicalService {

    @GET(ZGU_API_TICKET_TECHNICAL)
    suspend fun technical(
        @Header("Authorization") token: String,
        @Header("region") regionId: Int
    ) : ZGCDResponse<List<ZGTDRTicketTechnicalApiModel>>

}