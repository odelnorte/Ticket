package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketRegionDataSource {
    fun getRegion(request: ZGTDTicketRegionRequest): Flow<List<ZGTDTicketRegionResponse>>
}