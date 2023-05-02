package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketDetailRepositoryImpl @Inject constructor(
): ZGTDTicketDetailRepository {

    override fun getDetail(request: ZGTDTicketDetailRequest): Flow<ZGTDTicketDetailResponse> = flow {
        emit(
            ZGTDTicketDetailResponse(
                ticket = "MEX-627044-2023",
                room = "CALIENTE CULIACAN",
                description = "Cambio de piezas"
            )
        )
    }.onEach {

    }

}