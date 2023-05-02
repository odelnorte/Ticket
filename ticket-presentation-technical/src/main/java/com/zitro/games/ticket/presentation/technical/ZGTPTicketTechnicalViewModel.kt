package com.zitro.games.ticket.presentation.technical

import android.app.Application
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketTechnicalViewModel @Inject constructor(
    private val ticketTechnicalConverter: ZGTPTicketTechnicalConverter,
    private val ticketTechnicalUseCase: ZGTDTicketTechnicalUseCase,
    application: Application
) : MviViewModel<ZGPTicketTechnicalApiModel,
        UiState<ZGPTicketTechnicalApiModel>,
        ZGTPTicketTechnicalUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketTechnicalUiAction) {
        when(action) {
            is ZGTPTicketTechnicalUiAction.Loading -> {

            }

            is ZGTPTicketTechnicalUiAction.NotLoading -> {

            }

            is ZGTPTicketTechnicalUiAction.TechnicalDetail -> {
                submitSingleEvent(
                    ZGTPTicketTechnicalUiSingleEvent.Navigate(
                        NavRoutes.AtServiceTicketTechnicalDetail.routeForTicketTechnicalDetail(
                            action.data
                        )
                    )
                )
            }
        }
    }

    override fun initState(): UiState<ZGPTicketTechnicalApiModel> = UiState.NotInit



}