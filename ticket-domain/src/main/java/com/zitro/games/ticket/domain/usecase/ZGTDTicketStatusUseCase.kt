package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketStatusUseCase(
    configuration: Configuration,
    private val ticketStatusRepository: ZGTDTicketStatusRepository,
) : ZGDCUseCase<ZGTDTicketStatusUseCase.Request, ZGTDTicketStatusUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketStatusRepository.getStatus(request.statusRequest).map {
            Response(it)
        }

    data class Request(val statusRequest: ZGTDTicketStatusRequest) : ZGDCUseCase.Request

    data class Response(val statusResponse: List<ZGTDTicketStatusResponse>) : ZGDCUseCase.Response
}