package com.zitro.games.ticket.presentation.registration.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRoomsUseCase
import com.zitro.games.ticket.presentation.registration.ZGPTRoomsApiModel
import com.zitro.games.ticket.presentation.registration.ZGPTRoomsListModel
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRoomsConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRoomsUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRoomsUseCase.Response) =
        convert(data.roomResponse)

    private fun convert(statusResponse: List<ZGTDTicketRoomsResponse>): ZGPTRoomsApiModel {
        val listStatusApiModel = mutableListOf<ZGPTRoomsListModel>()

        statusResponse.forEach {
            listStatusApiModel.add(
                ZGPTRoomsListModel(
                    roomId = it.roomId,
                    roomName = it.roomName,
                    officeId = it.officeId
                )
            )
        }

        return ZGPTRoomsApiModel(
            listRooms = listStatusApiModel
        )
    }
}