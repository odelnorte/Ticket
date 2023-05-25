package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketRegionApiModel(
    @SerializedName("id")
    val regionId: Int,
    @SerializedName("nombre")
    val regionName: String
)


