package com.zitro.games.ticket.domain.usecase

import com.zitro.games.domain.common.usecase.ZGDCUseCase
import com.zitro.games.ticket.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ZGTDUseCaseModule {
    @Provides
    fun provideTicketStatusUseCase(configuration: ZGDCUseCase.Configuration, ticketStatusRepository: ZGTDTicketStatusRepository) =
        ZGTDTicketStatusUseCase(configuration, ticketStatusRepository)

    @Provides
    fun provideTicketRoomsUseCase(configuration: ZGDCUseCase.Configuration, ticketRoomsRepository: ZGTDTicketRoomsRepository) =
        ZGTDTicketRoomsUseCase(configuration, ticketRoomsRepository)

    @Provides
    fun provideTicketRegionUseCase(configuration: ZGDCUseCase.Configuration, ticketRegionRepository: ZGTDTicketRegionRepository) =
        ZGTDTicketRegionUseCase(configuration, ticketRegionRepository)

    @Provides
    fun provideTicketReassignUseCase(configuration: ZGDCUseCase.Configuration, ticketReassignRepository: ZGTDTicketReassignRepository) =
        ZGTDTicketReassignUseCase(configuration, ticketReassignRepository)

    @Provides
    fun provideTicketRegionWithFailureUseCase(
        configuration: ZGDCUseCase.Configuration,
        ticketRegionRepository: ZGTDTicketRegionRepository,
        ticketFailureRepository: ZGTDTicketFailureRepository
    ) = ZGTDTicketRegionWithFailureUseCase(configuration, ticketRegionRepository, ticketFailureRepository)

    @Provides
    fun provideTicketUpdateStatusUseCase(configuration: ZGDCUseCase.Configuration, ticketStatusRepository: ZGTDTicketStatusRepository) =
        ZGTDTicketUpdateStatusUseCase(configuration, ticketStatusRepository)

    @Provides
    fun provideTicketPendingUseCase(configuration: ZGDCUseCase.Configuration, ticketRoomsRepository: ZGTDTicketPendingRepository) =
        ZGTDTicketPendingUseCase(configuration, ticketRoomsRepository)

    @Provides
    fun provideTicketDetailUseCase(configuration: ZGDCUseCase.Configuration, ticketDetailRepository: ZGTDTicketDetailRepository) =
        ZGTDTicketDetailUseCase(configuration, ticketDetailRepository)

    @Provides
    fun provideTicketRegistrationUseCase(configuration: ZGDCUseCase.Configuration, ticketRegistrationRepository: ZGTDTicketRegistrationRepository) =
        ZGTDTicketRegistrationUseCase(configuration, ticketRegistrationRepository)


    @Provides
    fun provideTicketTechnicalUseCase(configuration: ZGDCUseCase.Configuration, ticketTechnicalRepository: ZGTDTicketTechnicalRepository) =
        ZGTDTicketTechnicalUseCase(configuration, ticketTechnicalRepository)

    @Provides
    fun provideTicketTechnicalDetailUseCase(configuration: ZGDCUseCase.Configuration, ticketTechnicalRepository: ZGTDTicketTechnicalDetailRepository) =
        ZGTDTicketTechnicalDetailUseCase(configuration, ticketTechnicalRepository)

    @Provides
    fun provideTicketFailureUseCase(configuration: ZGDCUseCase.Configuration, ticketFailureRepository: ZGTDTicketFailureRepository) =
        ZGTDTicketFailureUseCase(configuration, ticketFailureRepository)

    @Provides
    fun provideTicketMachineUseCase(configuration: ZGDCUseCase.Configuration, ticketMachineRepository: ZGTDTicketMachineRepository) =
        ZGTDTicketMachineUseCase(configuration, ticketMachineRepository)

}