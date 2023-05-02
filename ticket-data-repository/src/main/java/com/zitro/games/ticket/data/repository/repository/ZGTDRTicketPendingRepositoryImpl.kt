package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailRequest
import com.zitro.games.ticket.domain.entity.detail.ZGTDTicketDetailResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketPendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketPendingRepositoryImpl @Inject constructor(
): ZGTDTicketPendingRepository {

    override fun getPending(request: ZGTDTicketDetailRequest): Flow<List<ZGTDTicketDetailResponse>> = flow {
        emit(
            listOf(
                ZGTDTicketDetailResponse(
                        ticket = "MEX-627044-2023",
                        room = "CALIENTE CULIACAN",
                        description = "Cambio de piezas"
                ),
                ZGTDTicketDetailResponse(
                        ticket = "MEX-626138-2023",
                        room = "CASINO EL DORADO",
                        description = "Cero juegado"
                ),
                ZGTDTicketDetailResponse(
                        ticket = "MEX-626126-2023",
                        room = "CROWN CITY NANOJOA VS",
                        description = "Cero jugado"
                )
            )
        )
    }.onEach {

    }
}