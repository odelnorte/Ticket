package com.zitro.games.ticket.domain.entity.technical

import com.zitro.games.util.common.ZGC_TYPE_STATUS_DAY_OF_REST
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INABILITY
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INCIDENT
import com.zitro.games.util.common.ZGC_TYPE_STATUS_MIX
import com.zitro.games.util.common.ZGC_TYPE_STATUS_OUT_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_VACATION
import com.zitro.games.util.common.ZGUTypeStatus


data class ZGTDRTicketTechnicalResponse(
    val technicalId: Int,
    val regionId: Int,
    val technicalUser: ZGTDRTicketTechnicalUserResponse,
    val technicalStatus: ZGTDRTicketTechnicalStatusResponse
)

data class ZGTDRTicketTechnicalUserResponse(
    val userId: Int,
    val userName: String
)

data class ZGTDRTicketTechnicalStatusResponse(
    val statusId: Int,
    val statusName: String
){
    val typeStatus: ZGUTypeStatus = when(statusId) {
        ZGC_TYPE_STATUS_SERVICE -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
        ZGC_TYPE_STATUS_INCIDENT -> ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT
        ZGC_TYPE_STATUS_DAY_OF_REST -> ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST
        ZGC_TYPE_STATUS_MIX -> ZGUTypeStatus.ZGC_TYPE_STATUS_MIX
        ZGC_TYPE_STATUS_VACATION -> ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION
        ZGC_TYPE_STATUS_OUT_SERVICE -> ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
        ZGC_TYPE_STATUS_INABILITY -> ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
        else -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
    }
}

