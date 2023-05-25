package com.zitro.games.ticket.domain.entity

import com.zitro.games.domain.common.entity.ZGCDUseCaseException

sealed class ZGTDUseCaseException(cause: Throwable) : ZGCDUseCaseException(cause){

    class TicketStatusException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketRoomsException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketRegionException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketFailureException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketRegistrationException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketTechnicalException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketReassignException(cause: Throwable) : ZGTDUseCaseException(cause)
    class TicketMachineException(cause: Throwable) : ZGTDUseCaseException(cause)

}