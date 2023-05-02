package com.zitro.games.ticket.data.remote.source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zitro.games.ticket.data.remote.mock.ZGTDRDataMockTicketStatus.dataTicketStatus
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketStatusService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketStatusApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketStatusDataSourceImpl @Inject constructor(
    private val statusService: ZGTDRTicketStatusService
): ZGTDRRemoteTicketStatusDataSource {

    override fun getStatus(request: ZGTDTicketStatusRequest) = flow {
        emit(
            Gson().fromJson(
                dataTicketStatus,
                object : TypeToken<List<ZGTDRTicketStatusApiModel>>() {}.type
            ) as List<ZGTDRTicketStatusApiModel>
        )
    }.map {
        convert(it)
    }.catch {
        throw ZGTDUseCaseException.TicketStatusException(it)
    }


    private fun convert(listStatus: List<ZGTDRTicketStatusApiModel>): List<ZGTDTicketStatusResponse>{
        val listStatusResponse = mutableListOf<ZGTDTicketStatusResponse>()

        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketStatusResponse(
                    statusId = it.statusId,
                    statusType = it.statusType,
                    statusName = it.statusName,
                    statusDescription = it.statusDescription,
                )
            )
        }

         return listStatusResponse
    }

}