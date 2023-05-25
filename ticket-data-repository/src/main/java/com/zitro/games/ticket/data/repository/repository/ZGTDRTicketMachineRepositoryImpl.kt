package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketMachineDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketMachineDataSource
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineRequest
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketMachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketMachineRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketMachineDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketMachineDataSource,
): ZGTDTicketMachineRepository {

    override fun getMachine(request: ZGTDTicketMachineRequest): Flow<List<ZGTDTicketMachineResponse>> =
        localDataSource.getMachine(request).map {
            if (it.isEmpty()){
                return@map remoteDataSource.getMachine(request).onEach { list ->
                    localDataSource.setMachine(list, request.officeId)
                }
            } else{
                flow { emit(it) }
            }
    }.flattenConcat()
}