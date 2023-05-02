package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRoomsDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRoomsDataSource
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRoomsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketRoomsRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketRoomsDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketRoomsDataSource,
): ZGTDTicketRoomsRepository {

    override fun getRooms(request: ZGTDTicketRoomsRequest): Flow<List<ZGTDTicketRoomsResponse>> =
        remoteDataSource.getRooms(request).onEach {
            localDataSource.setRooms(it)
        }

}