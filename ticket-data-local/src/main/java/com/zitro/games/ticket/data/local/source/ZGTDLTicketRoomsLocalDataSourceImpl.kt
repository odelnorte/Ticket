package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRoomsDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLRoomsEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRoomsDataSource
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketRoomsLocalDataSourceImpl @Inject constructor(
    private val ticketRoomsDao: ZGCDLTicketRoomsDao,
) : ZGTDRLocalTicketRoomsDataSource {

    override fun getRooms(request: ZGTDTicketRoomsRequest): Flow<List<ZGTDTicketRoomsResponse>>  =
        ticketRoomsDao.getTicketRoomsEntity().map {
            convertResponse(it)
        }

    override suspend fun setRooms(listStatus: List<ZGTDTicketRoomsResponse>) {
        ticketRoomsDao.insertTicketRoomsEntity(convert(listStatus))
    }

    private fun convertResponse(listStatus: List<ZGCDLRoomsEntity>): List<ZGTDTicketRoomsResponse> {
        val listStatusResponse = mutableListOf<ZGTDTicketRoomsResponse>()
        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketRoomsResponse(
                    roomId = it.roomId,
                    roomName = it.roomName
                )
            )
        }

        return listStatusResponse

    }

    private fun convert(listStatus: List<ZGTDTicketRoomsResponse>): List<ZGCDLRoomsEntity> {
        val listStatusEntity = mutableListOf<ZGCDLRoomsEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLRoomsEntity(
                    roomId = it.roomId,
                    roomName = it.roomName
                )
            )
        }

        return listStatusEntity
    }
}