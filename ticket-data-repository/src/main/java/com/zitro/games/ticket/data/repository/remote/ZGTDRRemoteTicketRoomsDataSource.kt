package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketRoomsDataSource {
    fun getRooms(request: ZGTDTicketRoomsRequest): Flow<List<ZGTDTicketRoomsResponse>>
}