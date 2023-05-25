package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketTechnicalDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketTechnicalDataSource
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import com.zitro.games.ticket.domain.repository.ZGTDTicketTechnicalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketTechnicalRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketTechnicalDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketTechnicalDataSource,
): ZGTDTicketTechnicalRepository {

    override fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDRTicketTechnicalResponse>>  =
    localDataSource.getTechnical(request).map {
        if (it.isEmpty()){
            return@map remoteDataSource.getTechnical(request).onEach { list ->
                localDataSource.setTechnical(list)
            }
        } else{
            flow { emit(it) }
        }
    }.flattenConcat()
}