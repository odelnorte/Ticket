package com.zitro.games.ticket.presentation.registration.converters

import android.content.Context
import com.zitro.games.presentation.common.state.CommonResultConverter
import com.zitro.games.ticket.domain.entity.machine.ZGTDTicketMachineResponse
import com.zitro.games.ticket.domain.usecase.ZGTDTicketMachineUseCase
import com.zitro.games.ticket.presentation.registration.ZGPTMachineApiModel
import com.zitro.games.ticket.presentation.registration.ZGPTMachineListModel
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationApiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ZGTPTicketMachineConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<ZGTDTicketMachineUseCase.Response, ZGTPTicketRegistrationApiModel>() {

    override fun convertSuccess(data: ZGTDTicketMachineUseCase.Response) =
        convert(data.machineResponse)

    private fun convert(statusResponse: List<ZGTDTicketMachineResponse>): ZGPTMachineApiModel {
        val listStatusApiModel = mutableListOf<ZGPTMachineListModel>()

        statusResponse.forEach {
            listStatusApiModel.add(
                ZGPTMachineListModel(
                    machineId = it.machineId,
                    machineSeries = it.machineSeries,
                    machineModel = it.machineModel
                )
            )
        }

        return ZGPTMachineApiModel(
            listMachine = listStatusApiModel
        )
    }
}