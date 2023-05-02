package com.zitro.games.ticket.presentation.status

import com.zitro.games.util.common.*
import com.zitro.games.util.common.R

interface ZGTPTicketApiModel

data class ZGPTStatusApiModel(
    val listStatus: List<ZGPTStatusListModel> = listOf()
): ZGTPTicketApiModel

data class ZGPTRoomsApiModel(
    val listRooms: List<ZGPTRoomsListModel> = listOf()
): ZGTPTicketApiModel


data class ZGPTStatusListModel(
    val statusId: Int = 0,
    val statusType: Int = 0,
    val statusName: String = "",
    val statusImage: String = "",
    val statusDescription: String = ""
){
    fun getIcon() = when(statusType){
        ZGC_TYPE_STATUS_SERVICE -> {
            R.drawable.zgm_ic_service
        }
        ZGC_TYPE_STATUS_INCIDENT -> {
            R.drawable.zgm_ic_incident
        }
        ZGC_TYPE_STATUS_DAY_OF_REST -> {
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
    val roomId: Int = 0,
    val roomName: String = ""
)

