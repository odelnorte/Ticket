package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ZGCDLAppDataBase
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketFailureDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketMachineDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketReassignDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRegionDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRegistrationDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRoomsDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketStatusDao
import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketTechnicalDao
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
    @Provides
    fun provideTicketRegionDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketRegionDao = appDatabase.ticketRegion()
    @Provides
    fun provideTicketFailureDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketFailureDao = appDatabase.ticketFailure()
    @Provides
    fun provideTicketRegistrationDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketRegistrationDao = appDatabase.ticketRegistration()
    @Provides
    fun provideTicketTechnicalDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketTechnicalDao = appDatabase.ticketTechnical()
    @Provides
    fun provideTicketReassignDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketReassignDao = appDatabase.ticketReassign()
    @Provides
    fun provideTicketMachineDao(appDatabase: ZGCDLAppDataBase): ZGCDLTicketMachineDao = appDatabase.ticketMachine()
}