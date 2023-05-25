package com.zitro.games.ticket.presentation.registration

interface ZGTPTicketRegistrationApiModel

data class ZGPTRegistrationApiModel(
    val creationDate: String = "",
    val registrationId: Int = 0,
    val roomId: Int = 0,
    val machineId: String = "",
    val failureId: Int = 0,
    val failure: String = "",
    val technicalId: Int = 0,
    val statusId: Int = 0
): ZGTPTicketRegistrationApiModel


data class ZGPTRoomsApiModel(
    val listRooms: List<ZGPTRoomsListModel> = listOf()
): ZGTPTicketRegistrationApiModel

data class ZGPTRegionWithFailureApiModel(
    val listRegions: List<ZGPTRegionListModel> = listOf(),
    val listFailure: List<ZGPTFailureListModel> = listOf()
): ZGTPTicketRegistrationApiModel

data class ZGPTRegionApiModel(
    val listRegions: List<ZGPTRegionListModel> = listOf()
): ZGTPTicketRegistrationApiModel

data class ZGPTFailureApiModel(
    val listFailure: List<ZGPTFailureListModel> = listOf()
): ZGTPTicketRegistrationApiModel

data class ZGPTMachineApiModel(
    val listMachine: List<ZGPTMachineListModel> = listOf()
): ZGTPTicketRegistrationApiModel

data class ZGPTRoomsListModel(
    val roomId: Int? = null,
    val roomName: String? = null,
    val officeId: Int? = null
)

data class ZGPTRegionListModel(
    val regionId: Int? = null,
    val regionName: String? = null
)

data class ZGPTFailureListModel(
    val failureId: Int? = null,
    val failureName: String? = null
)

data class ZGPTMachineListModel(
    val machineId: Int? = null,
    val machineSeries: String? = null,
    val machineModel: String? = null
)

data class ZGPTMessageApiModel(
    val message: String = ""
): ZGTPTicketRegistrationApiModel