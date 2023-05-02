package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketPendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketPendingUseCase(
    configuration: Configuration,
    private val ticketPendingRepository: ZGTDTicketPendingRepository,
) : ZGDCUseCase<ZGTDTicketPendingUseCase.Request, ZGTDTicketPendingUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketPendingRepository.getPending(request.pendingRequest).map {
            Response(it)
        }

    data class Request(val pendingRequest: ZGTDTicketDetailRequest) : ZGDCUseCase.Request

    data class Response(val pendingResponse: List<ZGTDTicketDetailResponse>) : ZGDCUseCase.Response
}