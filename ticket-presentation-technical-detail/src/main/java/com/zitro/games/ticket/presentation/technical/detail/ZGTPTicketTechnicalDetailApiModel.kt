package com.zitro.games.ticket.presentation.technical.detail

import com.zitro.games.presentation.common.ui.model.ZGPCListModel

interface ZGTPTicketDetailApiModel

data class ZGPTicketProcessApiModel(
    val listStatus: List<ZGPCListModel<ZGPTicketProcessListModel>> = listOf()
): ZGTPTicketDetailApiModel

data class ZGPTicketRequestApiModel(
    val listStatus: List<ZGPCListModel<ZGPTicketRequestListModel>> = listOf()
): ZGTPTicketDetailApiModel


data class ZGPTicketProcessListModel(
    val ticket: String,
    val room: String,
    val description: String
)

data class ZGPTicketRequestListModel(
    val ticket: String,
    val room: String,
    val description: String
)
