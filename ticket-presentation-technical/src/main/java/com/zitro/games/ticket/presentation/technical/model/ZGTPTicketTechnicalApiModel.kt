package com.zitro.games.ticket.presentation.technical.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.presentation.common.ui.theme.GSVCError
import com.zitro.games.presentation.common.ui.theme.GSVCSuccess100
import com.zitro.games.presentation.common.ui.theme.GSVCText200
import com.zitro.games.util.common.ZGC_TYPE_STATUS_DAY_OF_REST
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INABILITY
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INCIDENT
import com.zitro.games.util.common.ZGC_TYPE_STATUS_MIX
import com.zitro.games.util.common.ZGC_TYPE_STATUS_OUT_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_VACATION
import com.zitro.games.util.common.ZGUTypeStatus

interface ZGTPTicketTechnicalApiModel

data class ZGPTicketTechnicalTechnicalApiModel(
    val listStatus: List<ZGPCListModel<ZGPTicketTechnicalListModel>> = listOf()
): ZGTPTicketTechnicalApiModel

data class ZGPTicketReassignApiModel(
    val ticketId: Int,
    val technicalId: Int
): ZGTPTicketTechnicalApiModel

data class ZGPTicketFilterApiModel(
    val ticketId: Int,
    val technicalId: Int
): ZGTPTicketTechnicalApiModel

data class ZGPTRegionApiModel(
    val listRegions: List<ZGPTRegionListModel> = listOf()
): ZGTPTicketTechnicalApiModel

data class ZGPTRegionListModel(
    val regionId: Int? = null,
    val regionName: String? = null
)

data class ZGPTicketTechnicalListModel(
    val technicalId: Int,
    val regionId: Int,
    val technicalUser: ZGTDRTicketTechnicalUserModel,
    val technicalStatus: ZGTDRTicketTechnicalStatusModel
)

data class ZGTDRTicketTechnicalUserModel(
    val userId: Int,
    val userName: String
)

data class ZGTDRTicketTechnicalStatusModel(
    val statusId: Int,
    val statusName: String
){
    private val typeStatus: ZGUTypeStatus = when(statusId) {
        ZGC_TYPE_STATUS_SERVICE -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
        ZGC_TYPE_STATUS_INCIDENT -> ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT
        ZGC_TYPE_STATUS_DAY_OF_REST -> ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST
        ZGC_TYPE_STATUS_MIX -> ZGUTypeStatus.ZGC_TYPE_STATUS_MIX
        ZGC_TYPE_STATUS_VACATION -> ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION
        ZGC_TYPE_STATUS_OUT_SERVICE -> ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
        ZGC_TYPE_STATUS_INABILITY -> ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY
        else -> ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE
    }


    val color: Color
        @Composable
        get() = when(typeStatus) {
            ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE -> GSVCSuccess100
            ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_MIX -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION -> GSVCError
            ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY -> GSVCError
        }
}
