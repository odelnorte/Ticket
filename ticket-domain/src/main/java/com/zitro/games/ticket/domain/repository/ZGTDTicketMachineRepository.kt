package com.zitro.games.ticket.domain.repository

import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDTicketMachineRepository {
    fun getMachine(request: ZGTDTicketMachineRequest): Flow<List<ZGTDTicketMachineResponse>>
}