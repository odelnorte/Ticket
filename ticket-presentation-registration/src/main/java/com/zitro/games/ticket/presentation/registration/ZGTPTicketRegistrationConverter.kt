package com.zitro.games.ticket.presentation.registration

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegistrationUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRegistrationConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRegistrationUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRegistrationUseCase.Response) =
        convert(data.registrationResponse)

    private fun convert(registrationResponse: ZGTDTicketRegistrationResponse): ZGTPTicketRegistrationApiModel {
        return ZGPTMessageApiModel(
            message = registrationResponse.message
        )
    }
}