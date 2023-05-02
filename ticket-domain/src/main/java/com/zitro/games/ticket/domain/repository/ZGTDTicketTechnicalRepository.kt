package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketTechnicalRepository {
    fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDTicketTechnicalResponse>>
}