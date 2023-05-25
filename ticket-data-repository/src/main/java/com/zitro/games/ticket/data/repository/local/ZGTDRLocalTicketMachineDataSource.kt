package com.zitro.games.ticket.data.repository.local

import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import kotlinx.coroutines.flow.Flow

interface ZGTDRLocalTicketMachineDataSource {
    fun getMachine(request: ZGTDTicketMachineRequest): Flow<List<ZGTDTicketMachineResponse>>
    suspend fun setMachine(listStatus: List<ZGTDTicketMachineResponse>, roomId: Int)
}