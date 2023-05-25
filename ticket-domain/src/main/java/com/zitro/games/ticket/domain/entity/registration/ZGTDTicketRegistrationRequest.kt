package com.zitro.games.ticket.domain.entity.registration

data class ZGTDTicketRegistrationRequest (
    val roomId: Int,
    val usuId: Long,
    val machineId: Int,
    val failureId: Int,
    val failure: String,
    val token: String
)