package com.zitro.games.ticket.presentation.adetail

import android.app.Application
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.usecase.ZGTDTicketDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZGTPPTicketDetailViewModel @Inject constructor(
    private val ticketDetailConverter: ZGTPPTicketDetailConverter,
    private val ticketDetailUseCase: ZGTDTicketDetailUseCase,
    application: Application
) : MviViewModel<ZGTPPTicketDetailApiModel,
        UiState<ZGTPPTicketDetailApiModel>,
        ZGTPTicketDetailUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketDetailUiAction) {
        when(action) {
            is ZGTPTicketDetailUiAction.Loading -> {

            }

            is ZGTPTicketDetailUiAction.NotLoading -> {

            }

        }
    }

    override fun initState(): UiState<ZGTPPTicketDetailApiModel> = UiState.Loading
}