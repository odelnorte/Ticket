package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ZGCDLAppDataBase
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRoomsDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketStatusDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideTicketStatusDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketStatusDao = appDatabase.ticketStatus()
    @Provides
    fun provideTicketRoomsDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketRoomsDao = appDatabase.ticketRooms()

}