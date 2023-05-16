package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketStatusApiModel(
    @SerializedName("id")
    val statusId: Int,
    @SerializedName("nombre")
    val statusName: String,
    @SerializedName("tareaReferente")
    val statusTaskReferring: String
)
