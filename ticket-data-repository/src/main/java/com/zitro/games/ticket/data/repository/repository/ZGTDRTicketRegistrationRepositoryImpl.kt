package com.zitro.games.ticket.data.repository.repository

import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegistrationDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegistrationDataSource
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationRequest
import com.zitro.games.ticket.domain.entity.registration.ZGTDTicketRegistrationResponse
import com.zitro.games.ticket.domain.repository.ZGTDTicketRegistrationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class ZGTDRTicketRegistrationRepositoryImpl @Inject constructor(
    private val localDataSource: ZGTDRLocalTicketRegistrationDataSource,
    private val remoteDataSource: ZGTDRRemoteTicketRegistrationDataSource,
): ZGTDTicketRegistrationRepository {

    override fun registration(request: ZGTDTicketRegistrationRequest): Flow<ZGTDTicketRegistrationResponse> =
        remoteDataSource.registration(request).onEach {
            localDataSource.setRegistration(it, request.usuId)
        }
}