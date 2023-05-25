package com.zitro.games.ticket.presentation.technical.convert

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketTechnicalUseCase
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketTechnicalListModel
import com.zitro.games.ticket.presentation.technical.model.ZGPTicketTechnicalTechnicalApiModel
import com.zitro.games.ticket.presentation.technical.model.ZGTDRTicketTechnicalStatusModel
import com.zitro.games.ticket.presentation.technical.model.ZGTDRTicketTechnicalUserModel
import com.zitro.games.ticket.presentation.technical.model.ZGTPTicketTechnicalApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketTechnicalConverter@Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketTechnicalUseCase.Response, ZGTPTicketTechnicalApiModel>() {

    override fun convertSuccess(data: ZGTDTicketTechnicalUseCase.Response) =
        convert(data.technicalResponse)


    private fun convert(result: List<ZGTDRTicketTechnicalResponse>) : ZGPTicketTechnicalTechnicalApiModel {
        val ticketPending = mutableListOf<ZGPCListModel<ZGPTicketTechnicalListModel>>()
        result.forEach {
            ticketPending.add(
                ZGPCListModel(
                    id = it.technicalId.toString(),
                    name = it.technicalUser.userName,
                    number = it.technicalStatus.statusName,
                    item = ZGPTicketTechnicalListModel(
                        technicalId = it.technicalId,
                        regionId = it.technicalId,
                        technicalUser = ZGTDRTicketTechnicalUserModel(
                            userId = it.technicalUser.userId,
                            userName = it.technicalUser.userName,
                        ),
                        technicalStatus = ZGTDRTicketTechnicalStatusModel(
                            statusId = it.technicalStatus.statusId,
                            statusName = it.technicalStatus.statusName,
                        )
                    )
                )
            )
        }

        return ZGPTicketTechnicalTechnicalApiModel(
            listStatus = ticketPending
        )
    }
}