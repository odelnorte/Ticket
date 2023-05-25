package com.zitro.games.ticket.data.repository.remote

import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRRemoteTicketMachineDataSource {
    fun getMachine(request: ZGTDTicketMachineRequest): Flow<List<ZGTDTicketMachineResponse>>
}