package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketMachineApiModel(
    @SerializedName("id")
    val machineId: Int,
    @SerializedName("serie")
    val machineSeries: String,
    @SerializedName("modelo")
    val machineModel: String
)
