package com.zitro.games.ticket.presentation.pending.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketPendingUseCase
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPending
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingApiModel
import com.zitro.games.ticket.presentation.pending.ZGTPPTicketPendingListApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPPTicketPendingConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketPendingUseCase.Response, ZGTPPTicketPendingApiModel>() {

    override fun convertSuccess(data: ZGTDTicketPendingUseCase.Response) =
        convert(data.pendingResponse)


    private fun convert(result: List<ZGTDTicketDetailResponse>) : ZGTPPTicketPendingApiModel {
        val ticketPending = mutableListOf<ZGPCListModel<ZGTPPTicketPending>>()
        result.forEach {
            ticketPending.add(
                ZGPCListModel(
                    id = it.ticket,
                    name = it.room,
                    number = it.description,
                    item = ZGTPPTicketPending(
                        ticket = it.ticket,
                        room = it.room,
                        description = it.description,
                    )
                )
            )
        }

        return ZGTPPTicketPendingListApiModel(
            data = ticketPending
        )
    }
}