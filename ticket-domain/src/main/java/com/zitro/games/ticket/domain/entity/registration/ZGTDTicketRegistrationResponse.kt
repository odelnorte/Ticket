package com.zitro.games.ticket.domain.entity.registration

data class ZGTDTicketRegistrationResponse (
    val creationDate: String,
    val registrationId: Int,
    val roomId: Int,
    val machineId: Int,
    val failureId: Int,
    val failure: String,
    val technicalId: Int,
    val statusId: Int
)