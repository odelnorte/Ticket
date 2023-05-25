package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketFailureRepository {
    fun getFailure(request: ZGTDTicketFailureRequest): Flow<List<ZGTDTicketFailureResponse>>
}