package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketUpdateStatusUseCase(
    configuration: Configuration,
    private val ticketStatusRepository: ZGTDTicketStatusRepository,
) : ZGDCUseCase<ZGTDTicketUpdateStatusUseCase.Request, ZGTDTicketUpdateStatusUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketStatusRepository.setStatus(request.statusRequest).map {
            Response(it)
        }

    data class Request(val statusRequest: ZGTDTicketUpdateStatusRequest) : ZGDCUseCase.Request

    data class Response(val statusResponse: ZGTDTicketUpdateStatusResponse) : ZGDCUseCase.Response
}