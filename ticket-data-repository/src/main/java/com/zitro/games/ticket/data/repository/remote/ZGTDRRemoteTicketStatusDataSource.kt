package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketStatusDataSource {
    fun getStatus(request: ZGTDTicketStatusRequest): Flow<List<ZGTDTicketStatusResponse>>
}