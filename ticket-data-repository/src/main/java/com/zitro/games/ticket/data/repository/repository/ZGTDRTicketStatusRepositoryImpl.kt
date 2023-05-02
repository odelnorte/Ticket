package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketStatusDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.repository.ZGTDTicketStatusRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketStatusRepositoryImpl @Inject constructor(
    private val localCatalogDataSource: ZGTDRLocalTicketStatusDataSource,
    private val remoteCatalogDataSource: ZGTDRRemoteTicketStatusDataSource,
): ZGTDTicketStatusRepository {

    override fun getStatus(request: ZGTDTicketStatusRequest) =
        remoteCatalogDataSource.getStatus(request).onEach {
            localCatalogDataSource.setStatus(it)
    }

}