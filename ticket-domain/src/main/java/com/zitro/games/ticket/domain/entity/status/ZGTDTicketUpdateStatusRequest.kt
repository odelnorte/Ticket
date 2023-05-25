package com.zitro.games.ticket.domain.entity.status

data class ZGTDTicketUpdateStatusRequest(
    val usuId: Long,
    val statusId: Int,
    val roomId: Int,
    val regionId: Int,
    val token: String
)