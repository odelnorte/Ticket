package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailRequest
import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketTechnicalDetailRepository {
    fun getTechnicalDetail(request: ZGTDTicketTechnicalDetailRequest): Flow<List<ZGTDTicketTechnicalDetailResponse>>
}