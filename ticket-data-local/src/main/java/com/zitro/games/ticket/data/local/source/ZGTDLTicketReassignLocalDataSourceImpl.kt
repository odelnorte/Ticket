package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketReassignDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLReassignEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketReassignDataSource
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketReassignLocalDataSourceImpl @Inject constructor(
    private val ticketReassignDao: ZGCDLTicketReassignDao,
) : ZGTDRLocalTicketReassignDataSource {
    override fun getReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse> =
        ticketReassignDao.getTicketReassignEntity(request.ticketId).map {
            convert(it)
    }

    override suspend fun setReassign(request: ZGTDTicketReassignRequest)  =
        flow { emit(ticketReassignDao.insertTicketReassignEntity(convert(request))) }


    private fun convert(reassign: ZGTDTicketReassignRequest): ZGCDLReassignEntity = ZGCDLReassignEntity(
            ticketId = reassign.ticketId,
            technicalId = reassign.technicalId,
        )

    private fun convert(reassign: ZGCDLReassignEntity): ZGTDTicketReassignResponse = ZGTDTicketReassignResponse(
            ticketId = reassign.ticketId,
            technicalId = reassign.technicalId,
        )
}