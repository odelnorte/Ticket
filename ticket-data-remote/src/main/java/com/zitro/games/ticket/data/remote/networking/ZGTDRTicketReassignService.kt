package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.util.common.ZGU_API_TICKET_REASSIGN
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.PUT

interface ZGTDRTicketReassignService {

    @FormUrlEncoded
    @PUT(ZGU_API_TICKET_REASSIGN)
    suspend fun reassign(
        @Header("Authorization") token: String,
        @Field("tecnicoId") technicalId: Int,
        @Field("id") ticketId: Int
    ) : ZGCDResponse<String>

}