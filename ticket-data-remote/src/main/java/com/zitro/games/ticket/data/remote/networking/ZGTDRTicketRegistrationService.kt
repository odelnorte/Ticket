package com.zitro.games.ticket.data.remote.networking

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRegistrationApiModel
import com.zitro.games.util.common.ZGU_API_TICKET_REGISTRATION
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ZGTDRTicketRegistrationService {

    @FormUrlEncoded
    @POST(ZGU_API_TICKET_REGISTRATION)
    suspend fun registration(
        @Header("Authorization") token: String,
        @Field("salaId") roomId : Int,
        @Field("maquinaId") machineId : Int,
        @Field("fallaId") failureId : Int,
        @Field("sintoma") failure : String
    ) : ZGCDResponse<ZGTDRTicketRegistrationApiModel>

}