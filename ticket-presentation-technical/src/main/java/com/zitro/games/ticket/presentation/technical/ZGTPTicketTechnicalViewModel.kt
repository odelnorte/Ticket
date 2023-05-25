package com.zitro.games.ticket.presentation.technical

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.state.MviViewModel
import com.zitro.games.presentation.common.state.UiSingleEvent
import com.zitro.games.presentation.common.state.UiState
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.usecase.ZGTDTicketReassignUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import com.zitro.games.ticket.presentation.technical.convert.ZGTPTicketReassignConverter
import com.zitro.games.ticket.presentation.technical.convert.ZGTPTicketRegionConverter
import com.zitro.games.ticket.presentation.technical.convert.ZGTPTicketTechnicalConverter
import com.zitro.games.ticket.presentation.technical.model.ZGTPTicketTechnicalApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZGTPTicketTechnicalViewModel @Inject constructor(
    private val ticketTechnicalConverter: ZGTPTicketTechnicalConverter,
    private val ticketTechnicalUseCase: ZGTDTicketTechnicalUseCase,
    private val ticketReassignConverter: ZGTPTicketReassignConverter,
    private val ticketReassignUseCase: ZGTDTicketReassignUseCase,
    private val ticketRegionUseCase: ZGTDTicketRegionUseCase,
    private val ticketRegionConverter: ZGTPTicketRegionConverter,
    application: Application
) : MviViewModel<ZGTPTicketTechnicalApiModel,
        UiState<ZGTPTicketTechnicalApiModel>,
        ZGTPTicketTechnicalUiAction, UiSingleEvent>(application) {
    override fun handleAction(action: ZGTPTicketTechnicalUiAction) {
        when(action) {
            is ZGTPTicketTechnicalUiAction.Loading -> {
                loadTechnical(action.data)
            }

            is ZGTPTicketTechnicalUiAction.NotLoading -> {

            }

            is ZGTPTicketTechnicalUiAction.TechnicalTicketReassign -> {
                reassign(action.data)
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

    override fun initState(): UiState<ZGTPTicketTechnicalApiModel> = UiState.NotLoading

    private fun loadTechnical(statusRequest: ZGTDTicketTechnicalRequest){
        viewModelScope.launch {
            ticketTechnicalUseCase.execute(ZGTDTicketTechnicalUseCase.Request(statusRequest)).map {
                ticketTechnicalConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

    private fun reassign(reassign: ZGTDTicketReassignRequest){
        viewModelScope.launch {
            ticketReassignUseCase.execute(ZGTDTicketReassignUseCase.Request(reassign)).map {
                ticketReassignConverter.convert(it)
            }.collect { state ->
                submitState(state)
            }
        }
    }

}