package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketFailureDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketFailureDataSource
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureRequest
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketFailureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketFailureRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketFailureDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketFailureDataSource,
): ZGTDTicketFailureRepository {

    override fun getFailure(request: ZGTDTicketFailureRequest): Flow<List<ZGTDTicketFailureResponse>> =
        localDataSource.getFailure().map {
            if (it.isEmpty()){
                return@map remoteDataSource.getFailure(request).onEach { list ->
                    localDataSource.setFailure(list)
                }
            } else{
                flow { emit(it) }
            }
        }.flattenConcat()

}