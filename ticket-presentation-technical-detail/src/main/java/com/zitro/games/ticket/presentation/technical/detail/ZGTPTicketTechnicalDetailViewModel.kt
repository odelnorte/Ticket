package com.zitro.games.ticket.presentation.technical.detail

import android.app.Application
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketTechnicalDetailViewModel @Inject constructor(
    private val ticketTechnicalDetailConverter: ZGTPPTicketTechnicalDetailConverter,
    private val ticketTechnicalDetailUseCase: ZGTDTicketTechnicalUseCase,
    application: Application
) : MviViewModel<ZGTPTicketDetailApiModel,
        UiState<ZGTPTicketDetailApiModel>,
        ZGTPTicketTechnicalDetailUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketTechnicalDetailUiAction) {
        when(action) {
            is ZGTPTicketTechnicalDetailUiAction.Loading -> {

            }

            is ZGTPTicketTechnicalDetailUiAction.NotLoading -> {

            }

            is ZGTPTicketTechnicalDetailUiAction.TicketDetail -> {
                submitSingleEvent(
                    ZGTPTicketTechnicalDetailUiSingleEvent.Navigate(
                        NavRoutes.AtServiceTicketDetail.routeForTicketDetail(
                            action.data
                        )
                    )
                )
            }
        }
    }

    override fun initState(): UiState<ZGTPTicketDetailApiModel> = UiState.NotLoading



}