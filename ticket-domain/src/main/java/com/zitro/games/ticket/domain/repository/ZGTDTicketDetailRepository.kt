package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketDetailRepository {
    fun getDetail(request: ZGTDTicketDetailRequest): Flow<ZGTDTicketDetailResponse>
}