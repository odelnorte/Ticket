package com.zitro.games.ticket.data.remote.injection

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketFailureService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketMachineService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketReassignService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRegionService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRegistrationService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRoomsService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketStatusService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketTechnicalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ZGTicketStatusNetworkModule {

    @Provides
    fun provideTicketStatusService(retrofit: Retrofit): ZGTDRTicketStatusService =
        retrofit.create(ZGTDRTicketStatusService::class.java)

    @Provides
    fun provideTicketRoomsServiceService(retrofit: Retrofit): ZGTDRTicketRoomsService =
        retrofit.create(ZGTDRTicketRoomsService::class.java)

    @Provides
    fun provideTicketRegionServiceService(retrofit: Retrofit): ZGTDRTicketRegionService =
        retrofit.create(ZGTDRTicketRegionService::class.java)

    @Provides
    fun provideTicketFailureServiceService(retrofit: Retrofit): ZGTDRTicketFailureService =
        retrofit.create(ZGTDRTicketFailureService::class.java)

    @Provides
    fun provideTicketRegistrationServiceService(retrofit: Retrofit): ZGTDRTicketRegistrationService =
        retrofit.create(ZGTDRTicketRegistrationService::class.java)

    @Provides
    fun provideTicketTechnicalServiceService(retrofit: Retrofit): ZGTDRTicketTechnicalService =
        retrofit.create(ZGTDRTicketTechnicalService::class.java)

    @Provides
    fun provideTicketReassignServiceService(retrofit: Retrofit): ZGTDRTicketReassignService =
        retrofit.create(ZGTDRTicketReassignService::class.java)

    @Provides
    fun provideTicketMachineServiceService(retrofit: Retrofit): ZGTDRTicketMachineService =
        retrofit.create(ZGTDRTicketMachineService::class.java)
}