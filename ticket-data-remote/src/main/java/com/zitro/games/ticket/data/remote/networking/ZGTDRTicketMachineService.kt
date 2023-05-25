package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketMachineApiModel
import com.zitro.games.util.common.ZGU_API_MACHINE_COMPONENT_SERIES
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZGTDRTicketMachineService {

    @GET(ZGU_API_MACHINE_COMPONENT_SERIES)
    suspend fun machine(
        @Header("Authorization") token: String,
        @Query("officeId") task : Int
    ) : ZGCDResponse<List<ZGTDRTicketMachineApiModel>>
}