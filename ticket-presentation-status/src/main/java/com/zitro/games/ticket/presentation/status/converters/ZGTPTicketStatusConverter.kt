package com.zitro.games.ticket.presentation.status.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketStatusUseCase
import com.zitro.games.ticket.presentation.status.ZGPTStatusApiModel
import com.zitro.games.ticket.presentation.status.ZGPTStatusListModel
import com.zitro.games.ticket.presentation.status.ZGTPTicketApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketStatusConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketStatusUseCase.Response, ZGTPTicketApiModel>() {

    override fun convertSuccess(data: ZGTDTicketStatusUseCase.Response) =
        convert(data.statusResponse)

    private fun convert(statusResponse: List<ZGTDTicketStatusResponse>): ZGPTStatusApiModel {
        val listStatusApiModel = mutableListOf<ZGPTStatusListModel>()

        statusResponse.forEach {
            listStatusApiModel.add(
                ZGPTStatusListModel(
                    statusId = it.statusId,
                    statusType = it.statusType,
                    statusName = it.statusName,
                    statusDescription = it.statusDescription,
                )
            )
        }

        return ZGPTStatusApiModel(
            listStatus = listStatusApiModel
        )
    }
}