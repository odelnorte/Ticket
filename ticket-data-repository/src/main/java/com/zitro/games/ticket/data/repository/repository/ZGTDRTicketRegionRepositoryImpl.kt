package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegionDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegionDataSource
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketRegionRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketRegionDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketRegionDataSource,
): ZGTDTicketRegionRepository {

    override fun getRegion(request: ZGTDTicketRegionRequest): Flow<List<ZGTDTicketRegionResponse>> =
        localDataSource.getRegion().map {
            if (it.isEmpty()){
                return@map remoteDataSource.getRegion(request).onEach { list ->
                    localDataSource.setRegion(list)
                }
            } else{
                flow { emit(it) }
            }
        }.flattenConcat()

}