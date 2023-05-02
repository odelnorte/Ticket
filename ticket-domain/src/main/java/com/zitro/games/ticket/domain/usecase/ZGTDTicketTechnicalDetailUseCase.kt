package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailRequest
import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketTechnicalDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketTechnicalDetailUseCase(
    configuration: Configuration,
    private val ticketTechnicalDetailRepository: ZGTDTicketTechnicalDetailRepository,
) : ZGDCUseCase<ZGTDTicketTechnicalDetailUseCase.Request, ZGTDTicketTechnicalDetailUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketTechnicalDetailRepository.getTechnicalDetail(request.pendingRequest).map {
            Response(it)
        }

    data class Request(val pendingRequest: ZGTDTicketTechnicalDetailRequest) : ZGDCUseCase.Request

    data class Response(val pendingResponse: List<ZGTDTicketTechnicalDetailResponse>) : ZGDCUseCase.Response
}