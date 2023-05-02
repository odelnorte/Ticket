package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketRoomsApiModel(
    @SerializedName("room_Id")
    val roomId: Int,
    @SerializedName("room_name")
    val roomName: String
)
