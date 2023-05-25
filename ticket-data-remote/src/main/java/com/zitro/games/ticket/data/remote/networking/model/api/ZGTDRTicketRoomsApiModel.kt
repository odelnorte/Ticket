package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketRoomsApiModel(
    @SerializedName("id")
    val roomId: Int,
    @SerializedName("nombre")
    val roomName: String,
    val officeId: Int
)
