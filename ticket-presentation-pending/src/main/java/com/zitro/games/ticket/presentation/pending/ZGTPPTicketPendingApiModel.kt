package com.zitro.games.ticket.presentation.pending

import com.zitro.games.presentation.common.ui.model.ZGPCListModel


interface ZGTPPTicketPendingApiModel

data class ZGTPPTicketPendingListApiModel(
    val data: List<ZGPCListModel<ZGTPPTicketPending>>
): ZGTPPTicketPendingApiModel


data class ZGTPPTicketPending(
    val ticket: String,
    val room: String,
    val description: String
)


data class ZGTPPTicketRoomsApiModel(
    val listRooms: List<ZGTPPTicketRoomsModel> = listOf()
): ZGTPPTicketPendingApiModel


data class ZGTPPTicketRoomsModel(
    val roomId: Int = 0,
    val roomName: String = ""
)