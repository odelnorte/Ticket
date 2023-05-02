package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketStatusApiModel(
    @SerializedName("status_Id")
    val statusId: Int,
    @SerializedName("status_type")
    val statusType: Int,
    @SerializedName("status_name")
    val statusName: String,
    @SerializedName("status_description")
    val statusDescription: String
)
