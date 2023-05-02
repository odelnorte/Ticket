package com.zitro.games.ticket.presentation.registration

interface ZGTPTicketRegistrationApiModel

data class ZGPTRegistrationApiModel(
    val client: String = "",
    val model: String = "",
    val noSeries: String = "",
    val failure: String = "",
    val additionalInfo: String = "",
): ZGTPTicketRegistrationApiModel

data class ZGPTMessageApiModel(
    val message: String = ""
): ZGTPTicketRegistrationApiModel