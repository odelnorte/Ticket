package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketRegionRepository {
    fun getRegion(request: ZGTDTicketRegionRequest): Flow<List<ZGTDTicketRegionResponse>>
}