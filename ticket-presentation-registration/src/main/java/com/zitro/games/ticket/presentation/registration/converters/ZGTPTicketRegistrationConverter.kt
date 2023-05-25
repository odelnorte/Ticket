package com.zitro.games.ticket.presentation.registration.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegistrationUseCase
import com.zitro.games.ticket.presentation.registration.ZGPTRegistrationApiModel
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRegistrationConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRegistrationUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRegistrationUseCase.Response) =
        convert(data.registrationResponse)

    private fun convert(registrationResponse: ZGTDTicketRegistrationResponse): ZGPTRegistrationApiModel = ZGPTRegistrationApiModel(
        creationDate = registrationResponse.creationDate,
        registrationId = registrationResponse.registrationId,
        roomId = registrationResponse.roomId,
        machineId = registrationResponse.machineId.toString(),
        failureId = registrationResponse.failureId,
        failure = registrationResponse.failure,
        technicalId = registrationResponse.technicalId,
        statusId = registrationResponse.statusId
    )
}