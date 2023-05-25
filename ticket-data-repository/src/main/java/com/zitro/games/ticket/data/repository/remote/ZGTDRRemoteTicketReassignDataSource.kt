package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketReassignDataSource {
    fun getReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse>
}