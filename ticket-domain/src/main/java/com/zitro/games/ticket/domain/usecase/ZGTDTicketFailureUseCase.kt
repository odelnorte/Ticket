package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketFailureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketFailureUseCase(
    configuration: Configuration,
    private val ticketRoomsRepository: ZGTDTicketFailureRepository,
) : ZGDCUseCase<ZGTDTicketFailureUseCase.Request, ZGTDTicketFailureUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketRoomsRepository.getFailure(request.failureRequest).map {
            Response(it)
        }

    data class Request(val failureRequest: ZGTDTicketFailureRequest) : ZGDCUseCase.Request

    data class Response(val failureResponse: List<ZGTDTicketFailureResponse>) : ZGDCUseCase.Response
}