package com.zitro.games.ticket.data.local.injection

import com.zitro.games.ticket.data.local.source.ZGTDLTicketFailureLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketMachineLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketReassignLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketRegionLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketRegistrationLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketRoomsLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketStatusLocalDataSourceImpl
import com.zitro.games.ticket.data.local.source.ZGTDLTicketTechnicalLocalDataSourceImpl
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketFailureDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketMachineDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketReassignDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegionDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegistrationDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRoomsDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketStatusDataSource
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketTechnicalDataSource
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
    abstract fun bindTicketMachineDataSource(lostDataSourceImpl: ZGTDLTicketMachineLocalDataSourceImpl): ZGTDRLocalTicketMachineDataSource

    @Binds
    abstract fun bindTicketMachineRepository(lostDataSourceImpl: ZGTDRTicketMachineRepositoryImpl): ZGTDTicketMachineRepository

    @Binds
    abstract fun bindTicketRegionDataSource(lostDataSourceImpl: ZGTDLTicketRegionLocalDataSourceImpl): ZGTDRLocalTicketRegionDataSource

    @Binds
    abstract fun bindTicketRegionRepository(lostDataSourceImpl: ZGTDRTicketRegionRepositoryImpl): ZGTDTicketRegionRepository

    @Binds
    abstract fun bindTicketReassignDataSource(lostDataSourceImpl: ZGTDLTicketReassignLocalDataSourceImpl): ZGTDRLocalTicketReassignDataSource

    @Binds
    abstract fun bindTicketReassignRepository(lostDataSourceImpl: ZGTDRTicketReassignRepositoryImpl): ZGTDTicketReassignRepository

    @Binds
    abstract fun bindTicketRegistrationDataSource(lostDataSourceImpl: ZGTDLTicketRegistrationLocalDataSourceImpl): ZGTDRLocalTicketRegistrationDataSource

    @Binds
    abstract fun bindTicketRegistrationRepository(lostDataSourceImpl: ZGTDRTicketRegistrationRepositoryImpl): ZGTDTicketRegistrationRepository

    @Binds
    abstract fun bindTicketFailureDataSource(lostDataSourceImpl: ZGTDLTicketFailureLocalDataSourceImpl): ZGTDRLocalTicketFailureDataSource

    @Binds
    abstract fun bindTicketFailureRepository(lostDataSourceImpl: ZGTDRTicketFailureRepositoryImpl): ZGTDTicketFailureRepository

    @Binds
    abstract fun bindTicketPendingRepository(lostDataSourceImpl: ZGTDRTicketPendingRepositoryImpl): ZGTDTicketPendingRepository

    @Binds
    abstract fun bindTicketDetailRepository(lostDataSourceImpl: ZGTDRTicketDetailRepositoryImpl): ZGTDTicketDetailRepository

    @Binds
    abstract fun bindTicketTechnicalDataSource(lostDataSourceImpl: ZGTDLTicketTechnicalLocalDataSourceImpl): ZGTDRLocalTicketTechnicalDataSource

    @Binds
    abstract fun bindTicketTechnicalRepository(lostDataSourceImpl: ZGTDRTicketTechnicalRepositoryImpl): ZGTDTicketTechnicalRepository

    @Binds
    abstract fun bindTicketTechnicalDetailRepository(lostDataSourceImpl: ZGTDRTicketTechnicalDetailRepositoryImpl): ZGTDTicketTechnicalDetailRepository

}