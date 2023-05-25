package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketTechnicalService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketTechnicalApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketTechnicalDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalStatusResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalUserResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketTechnicalDataSourceImpl @Inject constructor(
    private val technicalService: ZGTDRTicketTechnicalService
): ZGTDRRemoteTicketTechnicalDataSource {

    override fun getTechnical(request: ZGTDTicketTechnicalRequest) = flow {
        emit(
            technicalService.technical(request.token, request.regionId)
        )
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketTechnicalException(it)
    }


    private fun convert(listTechnical: List<ZGTDRTicketTechnicalApiModel>): List<ZGTDRTicketTechnicalResponse>{
        val listTechnicalResponse = mutableListOf<ZGTDRTicketTechnicalResponse>()

        listTechnical.forEach {
            listTechnicalResponse.add(
                ZGTDRTicketTechnicalResponse(
                    technicalId = it.technicalId,
                    regionId = it.technicalId,
                    technicalUser = ZGTDRTicketTechnicalUserResponse(
                        userId = it.technicalUser.userId,
                        userName = it.technicalUser.userName,
                    ),
                    technicalStatus = ZGTDRTicketTechnicalStatusResponse(
                        statusId = it.technicalStatus.statusId,
                        statusName = it.technicalStatus.statusName,
                    )
                )
            )
        }

         return listTechnicalResponse
    }

}