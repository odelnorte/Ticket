package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketTechnicalDataSource {
    fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDRTicketTechnicalResponse>>
    suspend fun setTechnical(listTechnical: List<ZGTDRTicketTechnicalResponse>)
}