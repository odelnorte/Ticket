package com.zitro.games.ticket.presentation.technical

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zitro.games.presentation.common.ui.model.ZGPCListModel
import com.zitro.games.presentation.common.ui.theme.GSVCError
import com.zitro.games.presentation.common.ui.theme.GSVCSuccess100
import com.zitro.games.presentation.common.ui.theme.GSVCText200
import com.zitro.games.util.common.ZGUTypeStatus

interface ZGTPTicketApiModel

data class ZGPTicketTechnicalApiModel(
    val listStatus: List<ZGPCListModel<ZGPTicketTechnicalListModel>> = listOf()
): ZGTPTicketApiModel


data class ZGPTicketTechnicalListModel(
    val technicalId: Int = 0,
    val technicalName: String = "",
    val status: ZGUTypeStatus = ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE,
){
    val color: Color
        @Composable
        get() = when(status) {
            ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE -> GSVCSuccess100
            ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_MIX -> GSVCText200
            ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION -> GSVCError
            ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY ->GSVCError
        }

    val statusName: String = when(status) {
            ZGUTypeStatus.ZGC_TYPE_STATUS_SERVICE -> "Disponible"
            ZGUTypeStatus.ZGC_TYPE_STATUS_INCIDENT -> "Ocupado"
            ZGUTypeStatus.ZGC_TYPE_STATUS_DAY_OF_REST -> "En descanso"
            ZGUTypeStatus.ZGC_TYPE_STATUS_MIX -> "Ocupado"
            ZGUTypeStatus.ZGC_TYPE_STATUS_VACATION -> "De vacaciones"
            ZGUTypeStatus.ZGC_TYPE_STATUS_INABILITY -> "Incapacidad"
        }
}
