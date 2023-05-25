package com.zitro.games.ticket.presentation.status.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.session.domain.usecase.ZGSDUpdateSessionUseCase
import com.zitro.games.ticket.presentation.status.ZGPTSessionUpdateStatusApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketSessionUpdateStatusConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGSDUpdateSessionUseCase.Response, ZGPTSessionUpdateStatusApiModel>() {

    override fun convertSuccess(data: ZGSDUpdateSessionUseCase.Response) =
        convert(data.session)

    private fun convert(update: Int) = ZGPTSessionUpdateStatusApiModel(
            update = update
        )

}