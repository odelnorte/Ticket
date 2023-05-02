package com.zitro.games.ticket.domain.entity

import com.zitro.games.domain.common.entity.ZGCDUseCaseException

sealed class ZGTDUseCaseException(cause: Throwable) : ZGCDUseCaseException(cause){

    class TicketStatusException(cause: Throwable) : ZGTDUseCaseException(cause)

}