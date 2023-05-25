package com.zitro.games.ticket.data.remote.injection

import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketFailureDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketMachineDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketReassignDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketRegionDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketRegistrationDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketRoomsDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketStatusDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketTechnicalDataSourceImpl
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketFailureDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketMachineDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketReassignDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegionDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegistrationDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRoomsDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketTechnicalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ZGTDRTicketStatusRemoteDataSourceModule {

    @Binds
    abstract fun bindTicketStatusDataSource(postDataSourceImpl: ZGTDRemoteTicketStatusDataSourceImpl): ZGTDRRemoteTicketStatusDataSource

    @Binds
    abstract fun bindTicketRoomsDataSource(postDataSourceImpl: ZGTDRemoteTicketRoomsDataSourceImpl): ZGTDRRemoteTicketRoomsDataSource

    @Binds
    abstract fun bindTicketRegionDataSource(postDataSourceImpl: ZGTDRemoteTicketRegionDataSourceImpl): ZGTDRRemoteTicketRegionDataSource

    @Binds
    abstract fun bindTicketFailureDataSource(postDataSourceImpl: ZGTDRemoteTicketFailureDataSourceImpl): ZGTDRRemoteTicketFailureDataSource

    @Binds
    abstract fun bindTicketRegistrationDataSource(postDataSourceImpl: ZGTDRemoteTicketRegistrationDataSourceImpl): ZGTDRRemoteTicketRegistrationDataSource

    @Binds
    abstract fun bindTicketTechnicalDataSource(postDataSourceImpl: ZGTDRemoteTicketTechnicalDataSourceImpl): ZGTDRRemoteTicketTechnicalDataSource

    @Binds
    abstract fun bindTicketReassignDataSource(postDataSourceImpl: ZGTDRemoteTicketReassignDataSourceImpl): ZGTDRRemoteTicketReassignDataSource

    @Binds
    abstract fun bindTicketMachineDataSource(postDataSourceImpl: ZGTDRemoteTicketMachineDataSourceImpl): ZGTDRRemoteTicketMachineDataSource


}