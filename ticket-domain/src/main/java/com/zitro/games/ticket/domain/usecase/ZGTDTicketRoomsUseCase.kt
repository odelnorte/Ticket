package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRoomsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketRoomsUseCase(
    configuration: Configuration,
    private val ticketRoomsRepository: ZGTDTicketRoomsRepository,
) : ZGDCUseCase<ZGTDTicketRoomsUseCase.Request, ZGTDTicketRoomsUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketRoomsRepository.getRooms(request.roomRequest).map {
            Response(it)
        }

    data class Request(val roomRequest: ZGTDTicketRoomsRequest) : ZGDCUseCase.Request

    data class Response(val roomResponse: List<ZGTDTicketRoomsResponse>) : ZGDCUseCase.Response
}