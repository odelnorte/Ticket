package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketRegistrationRepository {
    fun sendTicket(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse>
}