package com.zitro.games.ticket.presentation.technical.detail

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalDetailUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPPTicketTechnicalDetailConverter@Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketTechnicalDetailUseCase.Response, ZGTPTicketDetailApiModel>() {

    override fun convertSuccess(data: ZGTDTicketTechnicalDetailUseCase.Response) =
        convert(data.pendingResponse)


    private fun convert(result: List<ZGTDTicketTechnicalDetailResponse>) : ZGTPTicketDetailApiModel {
        val ticketPending = mutableListOf<ZGPCListModel<ZGPTicketProcessListModel>>()
        result.forEach {
            ticketPending.add(
                ZGPCListModel(
                    id = it.ticket,
                    name = it.room,
                    number = it.description,
                    item = ZGPTicketProcessListModel(
                        ticket = it.ticket,
                        room = it.room,
                        description = it.description,
                    )
                )
            )
        }

        return ZGPTicketProcessApiModel(
            listStatus = ticketPending
        )
    }
}