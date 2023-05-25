package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketMachineService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketMachineApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketMachineDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketMachineDataSourceImpl @Inject constructor(
    private val machineService: ZGTDRTicketMachineService
): ZGTDRRemoteTicketMachineDataSource {

    override fun getMachine(request: ZGTDTicketMachineRequest) = flow {
        emit(machineService.machine(request.token, request.officeId))
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketMachineException(it)
    }


    private fun convert(listStatus: List<ZGTDRTicketMachineApiModel>): List<ZGTDTicketMachineResponse>{
        val listStatusResponse = mutableListOf<ZGTDTicketMachineResponse>()

        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketMachineResponse(
                    machineId = it.machineId,
                    machineSeries = it.machineSeries,
                    machineModel = it.machineModel
                )
            )
        }

         return listStatusResponse
    }

}