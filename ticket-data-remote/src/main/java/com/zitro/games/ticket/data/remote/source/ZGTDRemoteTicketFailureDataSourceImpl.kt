package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketFailureService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketFailureApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketFailureDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketFailureDataSourceImpl @Inject constructor(
    private val regionService: ZGTDRTicketFailureService
): ZGTDRRemoteTicketFailureDataSource {

    override fun getFailure(request: ZGTDTicketFailureRequest) = flow {
        emit(
            regionService.failure(request.token)
        )
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketFailureException(it)
    }


    private fun convert(listFailure: List<ZGTDRTicketFailureApiModel>): List<ZGTDTicketFailureResponse>{
        val listFailureResponse = mutableListOf<ZGTDTicketFailureResponse>()

        listFailure.forEach {
            listFailureResponse.add(
                ZGTDTicketFailureResponse(
                    failureId = it.failureId,
                    failureName = it.failureName
                )
            )
        }

         return listFailureResponse
    }

}