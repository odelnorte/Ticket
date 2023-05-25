package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRoomsService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRoomsApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRoomsDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketRoomsDataSourceImpl @Inject constructor(
    private val roomsService: ZGTDRTicketRoomsService
): ZGTDRRemoteTicketRoomsDataSource {

    override fun getRooms(request: ZGTDTicketRoomsRequest) = flow {
        emit(roomsService.rooms(request.token, request.regionId))
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketRoomsException(it)
    }


    private fun convert(listStatus: List<ZGTDRTicketRoomsApiModel>): List<ZGTDTicketRoomsResponse>{
        val listStatusResponse = mutableListOf<ZGTDTicketRoomsResponse>()

        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketRoomsResponse(
                    roomId = it.roomId,
                    roomName = it.roomName,
                    officeId = it.officeId
                )
            )
        }

         return listStatusResponse
    }

}