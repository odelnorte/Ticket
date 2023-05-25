package com.zitro.games.ticket.data.remote.networking.model.api

import com.google.gson.annotations.SerializedName

data class ZGTDRTicketRegistrationApiModel(
    @SerializedName("fechaCreacion")
    val creationDate: String,
    @SerializedName("id")
    val registrationId: Int,
    @SerializedName("salaId")
    val roomId: Int,
    @SerializedName("maquinaId")
    val machineId: Int,
    @SerializedName("fallaId")
    val failureId: Int,
    @SerializedName("sintoma")
    val failure: String,
    @SerializedName("tecnicoAsignado")
    val technicalId: Int,
    @SerializedName("estatusId")
    val statusId: Int
)
