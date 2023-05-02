package com.zitro.games.ticket.presentation.registration

import android.app.Application
import com.zitro.games.presentation.common.navigation.CPDataUserApiModel
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalInput
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketRegistrationViewModel @Inject constructor(
    private val ticketRegistrationConverter: ZGTPTicketRegistrationConverter,
    private val ticketRegistrationUseCase: ZGTDTicketRegistrationUseCase,
    application: Application
): MviViewModel<ZGTPTicketRegistrationApiModel,
        UiState<ZGTPTicketRegistrationApiModel>,
        ZGTPTicketRegistrationUiAction, UiSingleEvent>(application) {

    override fun handleAction(action: ZGTPTicketRegistrationUiAction) {
        when(action) {
            is ZGTPTicketRegistrationUiAction.Loading -> {

            }

            is ZGTPTicketRegistrationUiAction.NotLoading -> {

            }

            is ZGTPTicketRegistrationUiAction.Technical -> {
                submitSingleEvent(
                    ZGTPTicketRegistrationUiSingleEvent.Navigation(
                        NavRoutes.AtServiceTicketTechnical.routeForTicketTechnical(
                            CPTicketTechnicalInput(
                                CPDataUserApiModel(
                                    action.usuId,
                                    ""
                                )
                            )
                        )
                    )
                )
            }
        }
    }

    override fun initState(): UiState<ZGTPTicketRegistrationApiModel> = UiState.NotInit
}