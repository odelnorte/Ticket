package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketFailureRepository
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ZGTDTicketRegionWithFailureUseCase(
    configuration: Configuration,
    private val ticketRegionRepository: ZGTDTicketRegionRepository,
    private val ticketFailureRepository: ZGTDTicketFailureRepository,
) : ZGDCUseCase<ZGTDTicketRegionWithFailureUseCase.Request, ZGTDTicketRegionWithFailureUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> = combine(
        ticketRegionRepository.getRegion(request.regionRequest),
        ticketFailureRepository.getFailure(request.failureRequest)
    ) { region, failure ->
        Response(region, failure)
    }

    data class Request(
        val regionRequest: ZGTDTicketRegionRequest,
        val failureRequest: ZGTDTicketFailureRequest
    ) : ZGDCUseCase.Request

    data class Response(
        val regionResponse: List<ZGTDTicketRegionResponse>,
        val failureResponse: List<ZGTDTicketFailureResponse>,
    ) : ZGDCUseCase.Response
}