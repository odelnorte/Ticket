package com.zitro.games.ticket.presentation.technical.convert

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketReassignUseCase
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketReassignApiModel
import com.zitro.games.ticket.presentation.technical.model.ZGTPTicketTechnicalApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketReassignConverter@Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketReassignUseCase.Response, ZGTPTicketTechnicalApiModel>() {

    override fun convertSuccess(data: ZGTDTicketReassignUseCase.Response) =
        convert(data.reassignResponse)


    private fun convert(result: ZGTDTicketReassignResponse) : ZGTPTicketTechnicalApiModel = ZGPTicketReassignApiModel(
        ticketId = result.ticketId,
        technicalId = result.technicalId
    )
}