package com.zitro.games.ticket.presentation.technical

import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest

sealed class ZGTPTicketTechnicalUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketTechnicalUiAction()
    data class TechnicalDetail(val data: CPTicketTechnicalDetailInput) : ZGTPTicketTechnicalUiAction()
    object NotLoading : ZGTPTicketTechnicalUiAction()
}