package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketMachineDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLMachineEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketMachineDataSource
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketMachineLocalDataSourceImpl @Inject constructor(
    private val ticketMachineDao: ZGCDLTicketMachineDao,
) : ZGTDRLocalTicketMachineDataSource {

    override fun getMachine(request: ZGTDTicketMachineRequest): Flow<List<ZGTDTicketMachineResponse>>  =
        ticketMachineDao.getTicketMachineEntity(request.officeId).map {
            convertResponse(it)
        }

    override suspend fun setMachine(listStatus: List<ZGTDTicketMachineResponse>, machineId: Int) {
        ticketMachineDao.insertTicketMachineEntity(convert(listStatus, machineId))
    }

    private fun convertResponse(listStatus: List<ZGCDLMachineEntity>): List<ZGTDTicketMachineResponse> {
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

    private fun convert(listStatus: List<ZGTDTicketMachineResponse>, machineId: Int): List<ZGCDLMachineEntity> {
        val listStatusEntity = mutableListOf<ZGCDLMachineEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLMachineEntity(
                    machineId = it.machineId,
                    machineSeries = it.machineSeries,
                    machineModel = it.machineModel,
                    officeId = machineId
                )
            )
        }

        return listStatusEntity
    }
}