package com.zitro.games.ticket.data.remote.source

import com.zitro.games.ticket.data.remote.networking.ZGTDRTicketRegionService
import com.zitro.games.ticket.data.remote.networking.model.api.ZGTDRTicketRegionApiModel
import com.zitro.games.ticket.data.repository.remote.ZGTDRRemoteTicketRegionDataSource
import com.zitro.games.ticket.domain.entity.ZGTDUseCaseException
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDRemoteTicketRegionDataSourceImpl @Inject constructor(
    private val regionService: ZGTDRTicketRegionService
): ZGTDRRemoteTicketRegionDataSource {

    override fun getRegion(request: ZGTDTicketRegionRequest) = flow {
        emit(
            regionService.region(request.token)
        )
    }.map {
        convert(it.data)
    }.catch {
        throw ZGTDUseCaseException.TicketRegionException(it)
    }


    private fun convert(listRegion: List<ZGTDRTicketRegionApiModel>): List<ZGTDTicketRegionResponse>{
        val listRegionResponse = mutableListOf<ZGTDTicketRegionResponse>()

        listRegion.forEach {
            listRegionResponse.add(
                ZGTDTicketRegionResponse(
                    regionId = it.regionId,
                    regionName = it.regionName
                )
            )
        }

         return listRegionResponse
    }

}