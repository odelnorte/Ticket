package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketReassignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketReassignUseCase(
    configuration: Configuration,
    private val ticketReassignRepository: ZGTDTicketReassignRepository,
) : ZGDCUseCase<ZGTDTicketReassignUseCase.Request, ZGTDTicketReassignUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketReassignRepository.updateReassign(request.reassignRequest).map {
            Response(it)
        }

    data class Request(val reassignRequest: ZGTDTicketReassignRequest) : ZGDCUseCase.Request

    data class Response(val reassignResponse: ZGTDTicketReassignResponse) : ZGDCUseCase.Response
}