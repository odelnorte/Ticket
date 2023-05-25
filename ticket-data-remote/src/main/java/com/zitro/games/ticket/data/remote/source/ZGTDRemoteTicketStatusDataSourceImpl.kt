package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketStatusService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketStatusApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketStatusDataSourceImpl @Inject constructor(
    private val statusService: ZGTDRTicketStatusService
): ZGTDRRemoteTicketStatusDataSource {

    override fun getStatus(request: ZGTDTicketStatusRequest) = flow {
        emit(
            statusService.status(
                request.token,
                request.task
            )
        )
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketStatusException(it)
    }

    override fun setStatus(request: ZGTDTicketUpdateStatusRequest) = flow {
        emit(
            statusService.updateStatus(
                request.token,
                request.statusId
            )
        )
    }.map {
        ZGTDTicketUpdateStatusResponse
    }.catch {
        throw ZGTDUseCaseException.TicketStatusException(it)
    }

    private fun convert(listStatus: List<ZGTDRTicketStatusApiModel>): List<ZGTDTicketStatusResponse>{
        val listStatusResponse = mutableListOf<ZGTDTicketStatusResponse>()

        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketStatusResponse(
                    statusId = it.statusId,
                    statusName = it.statusName,
                    statusTaskReferring = it.statusTaskReferring
                )
            )
        }
         return listStatusResponse
    }
}