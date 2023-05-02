package com.zitro.games.ticket.presentation.adetail

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketDetailUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPPTicketDetailConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketDetailUseCase.Response, ZGTPPTicketDetailApiModel>() {

    override fun convertSuccess(data: ZGTDTicketDetailUseCase.Response) =
        convert(data.detailResponse)

    private fun convert(result: ZGTDTicketDetailResponse) : ZGTPPTicketDetailApiModel  = ZGTPPTicketDetail(
        ticket = result.ticket,
        room = result.room,
        description = result.description,
    )
}