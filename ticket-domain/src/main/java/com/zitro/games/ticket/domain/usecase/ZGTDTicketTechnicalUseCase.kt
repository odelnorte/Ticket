package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.repository.ZGTDTicketTechnicalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketTechnicalUseCase(
    configuration: Configuration,
    private val ticketTechnicalRepository: ZGTDTicketTechnicalRepository,
) : ZGDCUseCase<ZGTDTicketTechnicalUseCase.Request, ZGTDTicketTechnicalUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketTechnicalRepository.getTechnical(request.technicalRequest).map {
            Response(it)
        }

    data class Request(val technicalRequest: ZGTDTicketTechnicalRequest) : ZGDCUseCase.Request

    data class Response(val technicalResponse: List<ZGTDRTicketTechnicalResponse>) : ZGDCUseCase.Response
}