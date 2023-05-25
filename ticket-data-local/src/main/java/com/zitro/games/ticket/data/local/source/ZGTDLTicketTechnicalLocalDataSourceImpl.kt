package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketTechnicalDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLTechnicalEntity
import com.zitro.games.common.data.local.entity.ticket.ZGTDRTicketTechnicalStatusEntity
import com.zitro.games.common.data.local.entity.ticket.ZGTDRTicketTechnicalUserEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketTechnicalDataSource
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalStatusResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDRTicketTechnicalUserResponse
import com.zitro.games.ticket.domain.entity.technical.ZGTDTicketTechnicalRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketTechnicalLocalDataSourceImpl @Inject constructor(
    private val ticketTechnicalDao: ZGCDLTicketTechnicalDao,
) : ZGTDRLocalTicketTechnicalDataSource {

    override fun getTechnical(request: ZGTDTicketTechnicalRequest): Flow<List<ZGTDRTicketTechnicalResponse>>  =
        ticketTechnicalDao.getTicketTechnicalEntity().map {
            convertResponse(it)
        }

    override suspend fun setTechnical(listTechnical: List<ZGTDRTicketTechnicalResponse>) {
        ticketTechnicalDao.insertTicketTechnicalEntity(convert(listTechnical))
    }

    private fun convertResponse(listStatus: List<ZGCDLTechnicalEntity>): List<ZGTDRTicketTechnicalResponse> {
        val listStatusResponse = mutableListOf<ZGTDRTicketTechnicalResponse>()
        listStatus.forEach {
            listStatusResponse.add(
                ZGTDRTicketTechnicalResponse(
                    technicalId = it.technicalId,
                    regionId = it.technicalId,
                    technicalUser = ZGTDRTicketTechnicalUserResponse(
                        userId = it.technicalUser.userId,
                        userName = it.technicalUser.userName,
                    ),
                    technicalStatus = ZGTDRTicketTechnicalStatusResponse(
                        statusId = it.technicalStatus.statusId,
                        statusName = it.technicalStatus.statusName,
                    )
                )
            )
        }

        return listStatusResponse

    }

    private fun convert(listStatus: List<ZGTDRTicketTechnicalResponse>): List<ZGCDLTechnicalEntity> {
        val listStatusEntity = mutableListOf<ZGCDLTechnicalEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLTechnicalEntity(
                    technicalId = it.technicalId,
                    regionId = it.technicalId,
                    technicalUser = ZGTDRTicketTechnicalUserEntity(
                        userId = it.technicalUser.userId,
                        userName = it.technicalUser.userName,
                    ),
                    technicalStatus = ZGTDRTicketTechnicalStatusEntity(
                        statusId = it.technicalStatus.statusId,
                        statusName = it.technicalStatus.statusName,
                    )
                )
            )
        }

        return listStatusEntity
    }
}