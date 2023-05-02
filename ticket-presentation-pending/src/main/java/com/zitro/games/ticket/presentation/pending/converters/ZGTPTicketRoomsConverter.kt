package com.zitro.games.ticket.presentation.pending.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingApiModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketRoomsApiModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketRoomsModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRoomsConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRoomsUseCase.Response, ZGTPPTicketPendingApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRoomsUseCase.Response) =
        convert(data.statusResponse)

    private fun convert(statusResponse: List<ZGTDTicketRoomsResponse>): ZGTPPTicketPendingApiModel {
        val listStatusApiModel = mutableListOf<ZGTPPTicketRoomsModel>()

        statusResponse.forEach {
            listStatusApiModel.add(
                ZGTPPTicketRoomsModel(
                    roomId = it.roomId,
                    roomName = it.roomName
                )
            )
        }

        return ZGTPPTicketRoomsApiModel(
            listRooms = listStatusApiModel
        )
    }
}