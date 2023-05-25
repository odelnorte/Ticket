package com.zitro.games.ticket.presentation.technical

import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest

sealed class ZGTPTicketTechnicalUiAction: UiAction {
    data class Loading(val data: ZGTDTicketTechnicalRequest) : ZGTPTicketTechnicalUiAction()
    data class TechnicalDetail(val data: CPTicketTechnicalDetailInput) : ZGTPTicketTechnicalUiAction()
    data class TechnicalTicketReassign(val data: ZGTDTicketReassignRequest) : ZGTPTicketTechnicalUiAction()
    object NotLoading : ZGTPTicketTechnicalUiAction()
}