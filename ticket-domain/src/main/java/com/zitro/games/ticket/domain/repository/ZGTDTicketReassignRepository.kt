package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketReassignRepository {
    fun updateReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse>
}