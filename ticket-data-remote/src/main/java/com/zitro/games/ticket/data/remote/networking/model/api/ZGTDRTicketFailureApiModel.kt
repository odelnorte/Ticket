package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketFailureApiModel(
    @SerializedName("id")
    val failureId: Int,
    @SerializedName("nombre")
    val failureName: String
)
