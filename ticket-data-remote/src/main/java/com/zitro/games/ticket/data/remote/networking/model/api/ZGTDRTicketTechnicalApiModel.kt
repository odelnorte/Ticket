package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketTechnicalApiModel(
    @SerializedName("id")
    val technicalId: Int,
    @SerializedName("regionId")
    val regionId: Int,
    @SerializedName("Usuario")
    val technicalUser: ZGTDRTicketTechnicalUserApiModel,
    @SerializedName("Estatus")
    val technicalStatus: ZGTDRTicketTechnicalStatusApiModel
)

data class ZGTDRTicketTechnicalUserApiModel(
    @SerializedName("id")
    val userId: Int,
    @SerializedName("nombre")
    val userName: String
)

data class ZGTDRTicketTechnicalStatusApiModel(
    @SerializedName("id")
    val statusId: Int,
    @SerializedName("nombre")
    val statusName: String
)


