package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketRegionDataSource {
    fun getRegion(): Flow<List<ZGTDTicketRegionResponse>>
    suspend fun setRegion(listRegion: List<ZGTDTicketRegionResponse>)
}