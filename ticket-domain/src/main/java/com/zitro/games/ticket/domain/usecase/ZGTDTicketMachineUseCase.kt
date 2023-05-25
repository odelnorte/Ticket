package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketMachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ZGTDTicketMachineUseCase(
    configuration: Configuration,
    private val ticketMachineRepository: ZGTDTicketMachineRepository,
) : ZGDCUseCase<ZGTDTicketMachineUseCase.Request, ZGTDTicketMachineUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        ticketMachineRepository.getMachine(request.machineRequest).map {
            Response(it)
        }

    data class Request(val machineRequest: ZGTDTicketMachineRequest) : ZGDCUseCase.Request

    data class Response(val machineResponse: List<ZGTDTicketMachineResponse>) : ZGDCUseCase.Response
}