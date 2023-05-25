package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketStatusDataSource {
    fun getStatus(): Flow<List<ZGTDTicketStatusResponse>>
    suspend fun setStatus(listStatus: List<ZGTDTicketStatusResponse>)
}