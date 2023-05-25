package com.zitro.games.ticket.presentation.registration

import com.zitro.games.presentation.common.navigation.CPDataUserApiModel
import com.zitro.games.presentation.common.navigation.input.ticket.model.CPDataTicketApiModel
import com.zitro.games.presentation.common.navigation.model.CPDataRegionApiModel
import com.zitro.games.presentation.common.state.UiAction
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.rooms.ZGTDTicketRoomsRequest

sealed class ZGTPTicketRegistrationUiAction: UiAction {
    data class Loading(val token: String) : ZGTPTicketRegistrationUiAction()
    data class LoadingRooms(val data: ZGTDTicketRoomsRequest) : ZGTPTicketRegistrationUiAction()
    data class LoadingMachine(val data: ZGTDTicketMachineRequest) : ZGTPTicketRegistrationUiAction()
    data class LoadingRegion(val data: ZGTDTicketRegionRequest) : ZGTPTicketRegistrationUiAction()
    data class LoadingFailure(val data: ZGTDTicketFailureRequest) : ZGTPTicketRegistrationUiAction()
    data class Registration(val data: ZGTDTicketRegistrationRequest) : ZGTPTicketRegistrationUiAction()
    data class Technical(
        val data: CPDataUserApiModel,
        val region: CPDataRegionApiModel,
        val ticket: CPDataTicketApiModel
    ) : ZGTPTicketRegistrationUiAction()
    object NotLoading : ZGTPTicketRegistrationUiAction()
}