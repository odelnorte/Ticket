package com.zitro.games.ticket.presentation.technical

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketTechnicalConverter@Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketTechnicalUseCase.Response, ZGTPTicketApiModel>() {

    override fun convertSuccess(data: ZGTDTicketTechnicalUseCase.Response) =
        convert(data.pendingResponse)


    private fun convert(result: List<ZGTDTicketTechnicalResponse>) : ZGPTicketTechnicalApiModel {
        val ticketPending = mutableListOf<ZGPCListModel<ZGPTicketTechnicalListModel>>()
        result.forEach {
            ticketPending.add(
                ZGPCListModel(
                    id = it.technicalId.toString(),
                    name = it.technicalName,
                    number = it.statusName,
                    item = ZGPTicketTechnicalListModel(
                        technicalId = it.technicalId,
                        technicalName = it.technicalName,
                        status = it.typeStatus,
                    )
                )
            )
        }

        return ZGPTicketTechnicalApiModel(
            listStatus = ticketPending
        )
    }
}