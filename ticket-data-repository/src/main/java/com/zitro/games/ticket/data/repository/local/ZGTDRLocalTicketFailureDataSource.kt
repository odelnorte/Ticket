package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketFailureDataSource {
    fun getFailure(): Flow<List<ZGTDTicketFailureResponse>>
    suspend fun setFailure(listFailure: List<ZGTDTicketFailureResponse>)
}