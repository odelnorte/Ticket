package com.zitro.games.ticket.presentation.status

import com.zitro.games.util.common.R
import com.zitro.games.util.common.ZGC_TYPE_STATUS_DAY_OF_REST
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INABILITY
import com.zitro.games.util.common.ZGC_TYPE_STATUS_INCIDENT
import com.zitro.games.util.common.ZGC_TYPE_STATUS_MIX
import com.zitro.games.util.common.ZGC_TYPE_STATUS_OUT_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_SERVICE
import com.zitro.games.util.common.ZGC_TYPE_STATUS_VACATION

interface ZGTPTicketApiModel

data class ZGPTStatusApiModel(
    val listStatus: List<ZGPTStatusListModel> = listOf()
): ZGTPTicketApiModel

data class ZGPTRoomsApiModel(
    val listRooms: List<ZGPTRoomsListModel> = listOf()
): ZGTPTicketApiModel

data class ZGPTRegionApiModel(
    val listRegions: List<ZGPTRegionListModel> = listOf()
): ZGTPTicketApiModel

object ZGPTUpdateStatusApiModel: ZGTPTicketApiModel

data class ZGPTSessionUpdateStatusApiModel(
    val update: Int
): ZGTPTicketApiModel


data class ZGPTStatusListModel(
    val statusId: Int? = null,
    val statusName: String? = "",
    val statusTaskReferring: String? = ""
){
    fun getIcon() = when(statusId){
        ZGC_TYPE_STATUS_SERVICE -> {
            R.drawable.zgm_ic_service
        }
        ZGC_TYPE_STATUS_INCIDENT -> {
            R.drawable.zgm_ic_incident
        }
        ZGC_TYPE_STATUS_OUT_SERVICE, ZGC_TYPE_STATUS_DAY_OF_REST -> {
            R.drawable.zgm_ic_day_of_rest
        }
        ZGC_TYPE_STATUS_MIX -> {
            R.drawable.zgm_ic_mix
        }
        ZGC_TYPE_STATUS_VACATION -> {
            R.drawable.zgm_ic_vacation
        }

        ZGC_TYPE_STATUS_INABILITY -> {
            R.drawable.zgm_ic_inability
        }

        else -> {
            throw Exception("This status is not recognized")
        }
    }
}

data class ZGPTRoomsListModel(
    val roomId: Int? = null,
    val roomName: String? = null
)

data class ZGPTRegionListModel(
    val regionId: Int? = null,
    val regionName: String? = null
)

