package com.zitro.games.ticket.data.remote.source

import com.zitro.games.domain.common.entity.ZGCDResponse
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketReassignService
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketReassignDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketReassignDataSourceImpl @Inject constructor(
    private val technicalService: ZGTDRTicketReassignService
): ZGTDRRemoteTicketReassignDataSource {

    var data: ZGCDResponse<String>? = null

    override fun getReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse> = flow {
        emit(
            technicalService.reassign(
                request.token,
                request.technicalId,
                request.ticketId
            )
        )
    }.map {
        data = it
        convert(request)
    }.catch {
        throw ZGTDUseCaseException.TicketReassignException(
            Throwable(
                message = it.message ?: data?.msg,
                cause = it.cause
            )
        )
    }

    private fun convert(request: ZGTDTicketReassignRequest): ZGTDTicketReassignResponse = ZGTDTicketReassignResponse(
        ticketId = request.ticketId,
        technicalId = request.technicalId,
    )
}