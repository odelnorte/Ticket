package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketTechnicalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketTechnicalRepositoryImpl @Inject constructor(
): ZGTDTicketTechnicalRepository {

    override fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDTicketTechnicalResponse>> = flow {
        emit(
            listOf<ZGTDTicketTechnicalResponse>()
        )
    }.onEach {

    }
}