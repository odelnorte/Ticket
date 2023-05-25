package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRegistrationService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRegistrationApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegistrationDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketRegistrationDataSourceImpl @Inject constructor(
    private val registrationService: ZGTDRTicketRegistrationService
): ZGTDRRemoteTicketRegistrationDataSource {

    override fun registration(request: ZGTDTicketRegistrationRequest) = flow {
        emit(
            registrationService.registration(
                request.token,
                request.roomId,
                request.machineId,
                request.failureId,
                request.failure
            )
        )
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketRegistrationException(it)
    }


    private fun convert(response: ZGTDRTicketRegistrationApiModel): ZGTDTicketRegistrationResponse = ZGTDTicketRegistrationResponse(
             creationDate = response.creationDate,
             registrationId = response.registrationId,
             roomId = response.roomId,
             machineId = response.machineId,
             failureId = response.failureId,
             failure = response.failure,
             technicalId = response.technicalId,
             statusId = response.statusId
         )

}