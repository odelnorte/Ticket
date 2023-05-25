package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRegistrationDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLRegistrationEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegistrationDataSource
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketRegistrationLocalDataSourceImpl @Inject constructor(
    private val ticketRegistrationDao: ZGCDLTicketRegistrationDao,
) : ZGTDRLocalTicketRegistrationDataSource {

    override fun getRegistration(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse>  =
        ticketRegistrationDao.getTicketRegistrationEntity(request.usuId).map {
            convertResponse(it)
        }

    override suspend fun setRegistration(response: ZGTDTicketRegistrationResponse, usuId: Long) {
        ticketRegistrationDao.insertTicketRegistrationEntity(convert(response, usuId))
    }

    private fun convertResponse(entity: ZGCDLRegistrationEntity): ZGTDTicketRegistrationResponse = ZGTDTicketRegistrationResponse(
        creationDate = entity.creationDate,
        registrationId = entity.registrationId,
        roomId = entity.roomId,
        machineId = entity.machineId,
        failureId = entity.failureId,
        failure = entity.failure,
        technicalId = entity.technicalId,
        statusId = entity.statusId
    )

    private fun convert(response: ZGTDTicketRegistrationResponse, usuId: Long): ZGCDLRegistrationEntity  =
        ZGCDLRegistrationEntity(
            creationDate = response.creationDate,
            registrationId = response.registrationId,
            roomId = response.roomId,
            machineId = response.machineId,
            failureId = response.failureId,
            failure = response.failure,
            technicalId = response.technicalId,
            statusId = response.statusId,
            usuId = usuId
        )
}