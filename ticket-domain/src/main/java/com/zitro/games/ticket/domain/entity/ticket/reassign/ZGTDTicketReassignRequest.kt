package com.zitro.games.ticket.domain.entity.ticket.reassign

data class ZGTDTicketReassignRequest (
    val token: String,
    val ticketId: Int,
    val technicalId: Int
)