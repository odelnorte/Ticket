package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketRegistrationRepositoryImpl @Inject constructor(
): ZGTDTicketRegistrationRepository {

    override fun sendTicket(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse> = flow {
        emit(
            ZGTDTicketRegistrationResponse(
                message = "Se registro con éxito el folio",
            )
        )
    }.onEach {

    }
}