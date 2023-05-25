package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketFailureDataSource {
    fun getFailure(request: ZGTDTicketFailureRequest): Flow<List<ZGTDTicketFailureResponse>>
}