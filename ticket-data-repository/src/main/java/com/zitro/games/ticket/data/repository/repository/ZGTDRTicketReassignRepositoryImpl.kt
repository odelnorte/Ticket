package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketReassignDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketReassignDataSource
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignRequest
import com.zitro.games.ticket.domain.entity.ticket.reassign.ZGTDTicketReassignResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketReassignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketReassignRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketReassignDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketReassignDataSource,
): ZGTDTicketReassignRepository {

    override fun updateReassign(request: ZGTDTicketReassignRequest): Flow<ZGTDTicketReassignResponse> =
        remoteDataSource.getReassign(request).onEach {
            localDataSource.setReassign(request)
        }
}