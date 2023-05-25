package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketRegionUseCase(
    configuration: Configuration,
    private val ticketRegionRepository: ZGTDTicketRegionRepository,
) : ZGDCUseCase<ZGTDTicketRegionUseCase.Request, ZGTDTicketRegionUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketRegionRepository.getRegion(request.regionRequest).map {
            Response(it)
        }

    data class Request(val regionRequest: ZGTDTicketRegionRequest) : ZGDCUseCase.Request

    data class Response(val regionResponse: List<ZGTDTicketRegionResponse>) : ZGDCUseCase.Response
}