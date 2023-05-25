package com.zitro.games.ticket.presentation.status.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.presentation.status.ZGPTRegionApiModel
import com.zitro.games.ticket.presentation.status.ZGPTRegionListModel
import com.zitro.games.ticket.presentation.status.ZGTPTicketApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRegionConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRegionUseCase.Response, ZGTPTicketApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRegionUseCase.Response) =
        convert(data.regionResponse)

    private fun convert(statusResponse: List<ZGTDTicketRegionResponse>): ZGPTRegionApiModel {
        val listRegionApiModel = mutableListOf<ZGPTRegionListModel>()

        statusResponse.forEach {
            listRegionApiModel.add(
                ZGPTRegionListModel(
                    regionId = it.regionId,
                    regionName = it.regionName
                )
            )
        }

        return ZGPTRegionApiModel(
            listRegions = listRegionApiModel
        )
    }
}