package com.zitro.games.ticket.data.local.source

import com.zitro.games.common.data.local.db.ticket.ZGCDLTicketRegionDao
import com.zitro.games.common.data.local.entity.ticket.ZGCDLRegionEntity
import com.zitro.games.ticket.data.repository.local.ZGTDRLocalTicketRegionDataSource
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionRequest
import com.zitro.games.ticket.domain.entity.region.ZGTDTicketRegionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ZGTDLTicketRegionLocalDataSourceImpl @Inject constructor(
    private val ticketRegionDao: ZGCDLTicketRegionDao,
) : ZGTDRLocalTicketRegionDataSource {

    override fun getRegion(): Flow<List<ZGTDTicketRegionResponse>>  =
        ticketRegionDao.getTicketRegionEntity().map {
            convertResponse(it)
        }

    override suspend fun setRegion(listRegion: List<ZGTDTicketRegionResponse>) {
        ticketRegionDao.insertTicketRegionEntity(convert(listRegion))
    }

    private fun convertResponse(listStatus: List<ZGCDLRegionEntity>): List<ZGTDTicketRegionResponse> {
        val listStatusResponse = mutableListOf<ZGTDTicketRegionResponse>()
        listStatus.forEach {
            listStatusResponse.add(
                ZGTDTicketRegionResponse(
                    regionId = it.regionId,
                    regionName = it.regionName
                )
            )
        }

        return listStatusResponse

    }

    private fun convert(listStatus: List<ZGTDTicketRegionResponse>): List<ZGCDLRegionEntity> {
        val listStatusEntity = mutableListOf<ZGCDLRegionEntity>()
        listStatus.forEach {
            listStatusEntity.add(
                ZGCDLRegionEntity(
                    regionId = it.regionId,
                    regionName = it.regionName
                )
            )
        }

        return listStatusEntity
    }
}