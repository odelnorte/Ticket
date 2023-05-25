package com.zitro.games.ticket.presentation.technical.convert

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionUseCase
import com.zitro.games.ticket.presentation.technical.model.ZGPTRegionApiModel
import com.zitro.games.ticket.presentation.technical.model.ZGPTRegionListModel
import com.zitro.games.ticket.presentation.technical.model.ZGTPTicketTechnicalApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRegionConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRegionUseCase.Response, ZGTPTicketTechnicalApiModel>() {

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