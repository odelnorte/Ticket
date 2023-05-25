package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketRegistrationDataSource {
    fun registration(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse>
}