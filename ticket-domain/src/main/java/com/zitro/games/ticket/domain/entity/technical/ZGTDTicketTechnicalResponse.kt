package com.zitro.games.ticket.domain.entity.technical

import com.zitro.games.util.common.ZGUTypeStatus

data class ZGTDTicketTechnicalResponse(
    val technicalId: Int,
    val technicalName: String,
    val status: Int,
){
    val typeStatus: ZGUTypeStatus = when(status) {
        1 -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
        2 -> ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT
        3 -> ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST
        4 -> ZGUTypeStatus.ZGC_TYPE_STATUS_MIX
        5 -> ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION
        6 -> ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
        else -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
    }

    val statusName: String = when(typeStatus) {
        ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE -> "Disponible"
        ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT -> "Ocupado"
        ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST -> "En descanso"
        ZGUTypeStatus.ZGC_TYPE_STATUS_MIX -> "Ocupado"
        ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION -> "De vacaciones"
        ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY -> "Incapacidad"
    }
}
