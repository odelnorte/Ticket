package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRegionApiModel
import com.zitro.games.util.common.ZGU_API_MACHINE_COMPONENT_REGION
import retrofit2.http.GET
import retrofit2.http.Header

interface ZGTDRTicketRegionService {

    @GET(ZGU_API_MACHINE_COMPONENT_REGION)
    suspend fun region(
        @Header("Authorization") token: String
    ) : ZGCDResponse<List<ZGTDRTicketRegionApiModel>>

}