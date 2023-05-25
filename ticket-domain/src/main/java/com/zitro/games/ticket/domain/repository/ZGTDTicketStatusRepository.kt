package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketStatusRepository {
    fun getStatus(request: ZGTDTicketStatusRequest): Flow<List<ZGTDTicketStatusResponse>>
    fun setStatus(request: ZGTDTicketUpdateStatusRequest): Flow<ZGTDTicketUpdateStatusResponse>
}