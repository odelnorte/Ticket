package com.zitro.games.ticket.presentation.technical.detail

import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest

sealed class ZGTPTicketTechnicalDetailUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketTechnicalDetailUiAction()
    data class TicketDetail(val data: CPTicketDetailInput) : ZGTPTicketTechnicalDetailUiAction()
    object NotLoading : ZGTPTicketTechnicalDetailUiAction()
}