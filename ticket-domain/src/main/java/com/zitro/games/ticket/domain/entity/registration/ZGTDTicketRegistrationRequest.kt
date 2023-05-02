package com.zitro.games.ticket.domain.entity.registration

data class ZGTDTicketRegistrationRequest (
    val client: String,
    val model: String,
    val noSeries: String,
    val failure: String,
    val additionalInfo: String,
)