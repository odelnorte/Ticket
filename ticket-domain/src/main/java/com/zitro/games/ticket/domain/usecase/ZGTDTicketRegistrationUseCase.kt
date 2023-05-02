package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketRegistrationUseCase(
    configuration: Configuration,
    private val ticketRegistrationRepository: ZGTDTicketRegistrationRepository,
) : ZGDCUseCase<ZGTDTicketRegistrationUseCase.Request, ZGTDTicketRegistrationUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketRegistrationRepository.sendTicket(request.registrationRequest).map {
            Response(it)
        }

    data class Request(val registrationRequest: ZGTDTicketRegistrationRequest) : ZGDCUseCase.Request

    data class Response(val registrationResponse: ZGTDTicketRegistrationResponse) : ZGDCUseCase.Response
}