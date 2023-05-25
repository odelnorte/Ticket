package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketReassignDataSource {
    fun getReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse>
    suspend fun setReassign(request: ZGTDTicketReassignRequest): Flow<Long>
}