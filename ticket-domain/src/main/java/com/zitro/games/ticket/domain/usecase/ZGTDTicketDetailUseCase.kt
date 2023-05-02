package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketDetailUseCase(
    configuration: Configuration,
    private val ticketDetailRepository: ZGTDTicketDetailRepository,
) : ZGDCUseCase<ZGTDTicketDetailUseCase.Request, ZGTDTicketDetailUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketDetailRepository.getDetail(request.detailRequest).map {
            Response(it)
        }

    data class Request(val detailRequest: ZGTDTicketDetailRequest) : ZGDCUseCase.Request

    data class Response(val detailResponse: ZGTDTicketDetailResponse) : ZGDCUseCase.Response
}