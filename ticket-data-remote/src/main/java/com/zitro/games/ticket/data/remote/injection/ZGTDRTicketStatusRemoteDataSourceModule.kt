package com.zitro.games.ticket.data.remote.injection

import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketRoomsDataSourceImpl
import com.zitro.games.ticket.data.remote.source.ZGTDRemoteTicketStatusDataSourceImpl
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRoomsDataSource
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketStatusDataSource
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


}