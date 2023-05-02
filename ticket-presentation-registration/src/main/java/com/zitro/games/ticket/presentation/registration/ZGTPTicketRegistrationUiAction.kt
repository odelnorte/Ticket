package com.zitro.games.ticket.presentation.registration

import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest

sealed class ZGTPTicketRegistrationUiAction: UiAction {
    data class Loading(val data: ZGTDTicketStatusRequest) : ZGTPTicketRegistrationUiAction()
    data class Technical(val usuId: Long) : ZGTPTicketRegistrationUiAction()
    object NotLoading : ZGTPTicketRegistrationUiAction()
}