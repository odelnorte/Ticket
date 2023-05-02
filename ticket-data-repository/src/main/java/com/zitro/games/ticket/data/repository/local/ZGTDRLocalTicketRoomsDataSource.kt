package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketRoomsDataSource {
    fun getRooms(request: ZGTDTicketRoomsRequest): Flow<List<ZGTDTicketRoomsResponse>>
    suspend fun setRooms(listStatus: List<ZGTDTicketRoomsResponse>)
}