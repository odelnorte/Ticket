package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketTechnicalDataSource {
    fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDRTicketTechnicalResponse>>
}