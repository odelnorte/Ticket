package com.zitro.games.ticket.domain.entity.status

data class ZGTDTicketStatusResponse(
    val statusId: Int,
    val statusType: Int,
    val statusName: String,
    val statusDescription: String
)
