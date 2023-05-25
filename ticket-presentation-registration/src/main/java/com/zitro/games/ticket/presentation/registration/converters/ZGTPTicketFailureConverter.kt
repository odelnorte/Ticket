package com.zitro.games.ticket.presentation.registration.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketFailureUseCase
import com.zitro.games.ticket.presentation.registration.ZGPTFailureApiModel
import com.zitro.games.ticket.presentation.registration.ZGPTFailureListModel
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketFailureConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketFailureUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketFailureUseCase.Response) =
        convert(data.failureResponse)

    private fun convert(statusResponse: List<ZGTDTicketFailureResponse>): ZGPTFailureApiModel {
        val listFailureApiModel = mutableListOf<ZGPTFailureListModel>()

        statusResponse.forEach {
            listFailureApiModel.add(
                ZGPTFailureListModel(
                    failureId = it.failureId,
                    failureName = it.failureName
                )
            )
        }

        return ZGPTFailureApiModel(
            listFailure = listFailureApiModel
        )
    }
}