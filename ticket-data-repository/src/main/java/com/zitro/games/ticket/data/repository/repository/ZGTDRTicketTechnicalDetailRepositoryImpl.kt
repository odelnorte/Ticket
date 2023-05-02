package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailRequest
import com.zitro.games.ticket.domain.entity.technical.detail.ZGTDTicketTechnicalDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketTechnicalDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketTechnicalDetailRepositoryImpl @Inject constructor(
): ZGTDTicketTechnicalDetailRepository {
    override fun getTechnicalDetail(request: ZGTDTicketTechnicalDetailRequest): Flow<List<ZGTDTicketTechnicalDetailResponse>> = flow {
        emit(
            listOf<ZGTDTicketTechnicalDetailResponse>()
        )
    }.onEach {

    }
}