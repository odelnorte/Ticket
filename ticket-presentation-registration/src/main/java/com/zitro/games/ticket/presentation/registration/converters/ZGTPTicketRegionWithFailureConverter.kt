package com.zitro.games.ticket.presentation.registration.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketRegionWithFailureUseCase
import com.zitro.games.ticket.presentation.registration.ZGPTFailureListModel
import com.zitro.games.ticket.presentation.registration.ZGPTRegionListModel
import com.zitro.games.ticket.presentation.registration.ZGPTRegionWithFailureApiModel
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketRegionWithFailureConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketRegionWithFailureUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketRegionWithFailureUseCase.Response) =
        convert(data.regionResponse, data.failureResponse)

    private fun convert(
        regionResponse: List<ZGTDTicketRegionResponse>,
        failureResponse: List<ZGTDTicketFailureResponse>,
    ): ZGPTRegionWithFailureApiModel {
        val listRegionApiModel = mutableListOf<ZGPTRegionListModel>()
        val listFailureApiModel = mutableListOf<ZGPTFailureListModel>()

        regionResponse.forEach {
            listRegionApiModel.add(
                ZGPTRegionListModel(
                    regionId = it.regionId,
                    regionName = it.regionName
                )
            )
        }

        failureResponse.forEach {
            listFailureApiModel.add(
                ZGPTFailureListModel(
                    failureId = it.failureId,
                    failureName = it.failureName
                )
            )
        }

        return ZGPTRegionWithFailureApiModel(
            listRegions = listRegionApiModel,
            listFailure = listFailureApiModel,
        )
    }
}