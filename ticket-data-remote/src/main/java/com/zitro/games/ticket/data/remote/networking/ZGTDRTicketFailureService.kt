package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketFailureApiModel
import com.zitro.games.util.common.ZGU_API_MACHINE_COMPONENT_FAILURE
import retrofit2.http.GET
import retrofit2.http.Header

interface ZGTDRTicketFailureService {

    @GET(ZGU_API_MACHINE_COMPONENT_FAILURE)
    suspend fun failure(
        @Header("Authorization") token: String
    ) : ZGCDResponse<List<ZGTDRTicketFailureApiModel>>

}