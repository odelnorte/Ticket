package com.zitro.games.ticket.presentation.status.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.usecase.ZGTDTicketUpdateStatusUseCase
import com.zitro.games.ticket.presentation.status.ZGPTUpdateStatusApiModel
import com.zitro.games.ticket.presentation.status.ZGTPTicketApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketUpdateStatusConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketUpdateStatusUseCase.Response, ZGTPTicketApiModel>() {

    override fun convertSuccess(data: ZGTDTicketUpdateStatusUseCase.Response) = ZGPTUpdateStatusApiModel

}