package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketStatusDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLStatusEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketStatusDataSource
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusRequest
import com.zitro.games.ticket.domain.entity.status.ZGTDTicketStatusResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketStatusLocalDataSourceImpl @Inject constructor(
    private val zgcdlTicketStatusDao: ZGCDLTicketStatusDao,
) : ZGTDRLocalTicketStatusDataSource {
    override fun getStatus(request: ZGTDTicketStatusRequest): Flow<List<ZGTDTicketStatusResponse>> =
        zgcdlTicketStatusDao.getTicketStatusEntity().map {
            convertResponse(it)
    }

    override suspend fun setStatus(listStatus: List<ZGTDTicketStatusResponse>) {
        zgcdlTicketStatusDao.insertTicketStatusEntity(convert(listStatus))
    }

    private fun convertResponse(listStatus: List<ZGCDLStatusEntity>): List<ZGTDTicketStatusResponse> {
        val listStatusResponse = mutableListOf<ZGTDTicketStatusResponse>()
        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketStatusResponse(
                    statusId = it.statusId,
                    statusName = it.statusName,
                    statusTaskReferring = it.statusTaskReferring
                )
            )
        }

        return listStatusResponse

    }

    private fun convert(listStatus: List<ZGTDTicketStatusResponse>): List<ZGCDLStatusEntity> {
        val listStatusEntity = mutableListOf<ZGCDLStatusEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLStatusEntity(
                    statusId = it.statusId,
                    statusName = it.statusName,
                    statusTaskReferring = it.statusTaskReferring
                )
            )
        }

        return listStatusEntity
    }
}