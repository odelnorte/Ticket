package com.zitro.games.ticket.presentation.adetail

import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest

sealed class ZGTPTicketDetailUiAction: UiAction {
    data class Loading(val data: ZGTDTicketDetailRequest) : ZGTPTicketDetailUiAction()
    object NotLoading : ZGTPTicketDetailUiAction()
}