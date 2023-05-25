package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketStatusDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketUpdateStatusRequest
import com.zitro.games.ticket.domain.repository.ZGTDTicketStatusRepository
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketStatusRepositoryImpl @Inject constructor(
    private val localCatalogDataSource: ZGTDRLocalTicketStatusDataSource,
    private val remoteCatalogDataSource: ZGTDRRemoteTicketStatusDataSource,
): ZGTDTicketStatusRepository {

    override fun getStatus(request: ZGTDTicketStatusRequest) =
        localCatalogDataSource.getStatus().map {
            if (it.isEmpty()){
                return@map remoteCatalogDataSource.getStatus(request).onEach { list ->
                    localCatalogDataSource.setStatus(list)
                }
            } else{
                flow { emit(it) }
            }
        }.flattenConcat()

    override fun setStatus(request: ZGTDTicketUpdateStatusRequest)=
        remoteCatalogDataSource.setStatus(request)
}