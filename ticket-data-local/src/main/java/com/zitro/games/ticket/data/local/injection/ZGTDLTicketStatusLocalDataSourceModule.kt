package com.zitro.games.ticket.data.local.injection

import com.zitro.games.ticket.data.local.source.ZGTDLTicketRoomsLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketStatusLocalDataSourceImpl
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRoomsDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketStatusDataSource
import com.zitro.games.ticket.data.repository.repository.*
import com.zitro.games.ticket.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ZGTDLTicketStatusLocalDataSourceModule {

    @Binds
    abstract fun bindTicketStatusDataSource(lostDataSourceImpl: ZGTDLTicketStatusLocalDataSourceImpl): ZGTDRLocalTicketStatusDataSource


    @Binds
    abstract fun bindTicketStatusRepository(lostDataSourceImpl: ZGTDRTicketStatusRepositoryImpl): ZGTDTicketStatusRepository

    @Binds
    abstract fun bindTicketRoomsDataSource(lostDataSourceImpl: ZGTDLTicketRoomsLocalDataSourceImpl): ZGTDRLocalTicketRoomsDataSource

    @Binds
    abstract fun bindTicketRoomsRepository(lostDataSourceImpl: ZGTDRTicketRoomsRepositoryImpl): ZGTDTicketRoomsRepository

    @Binds
    abstract fun bindTicketPendingRepository(lostDataSourceImpl: ZGTDRTicketPendingRepositoryImpl): ZGTDTicketPendingRepository

    @Binds
    abstract fun bindTicketDetailRepository(lostDataSourceImpl: ZGTDRTicketDetailRepositoryImpl): ZGTDTicketDetailRepository

    @Binds
    abstract fun bindTicketRegistrationRepository(lostDataSourceImpl: ZGTDRTicketRegistrationRepositoryImpl): ZGTDTicketRegistrationRepository

    @Binds
    abstract fun bindTicketTechnicalRepository(lostDataSourceImpl: ZGTDRTicketTechnicalRepositoryImpl): ZGTDTicketTechnicalRepository

    @Binds
    abstract fun bindTicketTechnicalDetailRepository(lostDataSourceImpl: ZGTDRTicketTechnicalDetailRepositoryImpl): ZGTDTicketTechnicalDetailRepository

}