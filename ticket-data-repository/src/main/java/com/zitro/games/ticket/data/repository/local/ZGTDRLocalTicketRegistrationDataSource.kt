package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketRegistrationDataSource {
    fun getRegistration(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse>
    suspend fun setRegistration(response: ZGTDTicketRegistrationResponse, usuId: Long)


}