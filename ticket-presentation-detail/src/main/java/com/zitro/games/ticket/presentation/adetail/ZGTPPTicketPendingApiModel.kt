package com.zitro.games.ticket.presentation.adetail

interface ZGTPPTicketDetailApiModel

data class ZGTPPTicketDetail(
    val ticket: String,
    val room: String,
    val description: String
): ZGTPPTicketDetailApiModel
