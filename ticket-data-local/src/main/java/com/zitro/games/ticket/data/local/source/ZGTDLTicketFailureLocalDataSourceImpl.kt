package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketFailureDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLFailureEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketFailureDataSource
import com.zitro.games.ticket.domain.entity.failure.ZGTDTicketFailureResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketFailureLocalDataSourceImpl @Inject constructor(
    private val ticketFailureDao: ZGCDLTicketFailureDao,
) : ZGTDRLocalTicketFailureDataSource {

    override fun getFailure(): Flow<List<ZGTDTicketFailureResponse>>  =
        ticketFailureDao.getTicketFailureEntity().map {
            convertResponse(it)
        }

    override suspend fun setFailure(listFailure: List<ZGTDTicketFailureResponse>) {
        ticketFailureDao.insertTicketFailureEntity(convert(listFailure))
    }

    private fun convertResponse(listStatus: List<ZGCDLFailureEntity>): List<ZGTDTicketFailureResponse> {
        val listStatusResponse = mutableListOf<ZGTDTicketFailureResponse>()
        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketFailureResponse(
                    failureId = it.failureId,
                    failureName = it.failureName
                )
            )
        }

        return listStatusResponse

    }

    private fun convert(listStatus: List<ZGTDTicketFailureResponse>): List<ZGCDLFailureEntity> {
        val listStatusEntity = mutableListOf<ZGCDLFailureEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLFailureEntity(
                    failureId = it.failureId,
                    failureName = it.failureName
                )
            )
        }

        return listStatusEntity
    }
}