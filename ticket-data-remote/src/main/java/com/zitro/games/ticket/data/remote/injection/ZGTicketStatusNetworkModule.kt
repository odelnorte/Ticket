package com.zitro.games.ticket.data.remote.injection

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRoomsService
import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketStatusService
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
}