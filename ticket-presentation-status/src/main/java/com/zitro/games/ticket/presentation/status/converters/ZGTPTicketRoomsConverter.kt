package com.zitro.games.ticket.presentation.status.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.status.ZGPTRoomsApiModel
import com.zitro.games.ticket.presentation.status.ZGPTRoomsListModel
import com.zitro.games.ticket.presentation.status.ZGTPTicketApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRoomsConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRoomsUseCase.Response, ZGTPTicketApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRoomsUseCase.Response) =
        convert(data.statusResponse)

    private fun convert(statusResponse: List<ZGTDTicketRoomsResponse>): ZGPTRoomsApiModel {
        val listStatusApiModel = mutableListOf<ZGPTRoomsListModel>()

        statusResponse.forEach {
            listStatusApiModel.add(
                ZGPTRoomsListModel(
                    roomId = it.roomId,
                    roomName = it.roomName
                )
            )
        }

        return ZGPTRoomsApiModel(
            listRooms = listStatusApiModel
        )
    }
}