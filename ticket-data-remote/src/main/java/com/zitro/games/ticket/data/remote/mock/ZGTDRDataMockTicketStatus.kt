package com.zitro.games.ticket.data.remote.mock

object ZGTDRDataMockTicketStatus {
    val dataTicketStatus = """
        [
            {
                "status_Id": 1,
                "status_type": 1,
                "status_name": "En Servicio",
                "status_description": "Te muestra disponible para que te asignen actividades"
            },
            {
                "status_Id": 2,
                "status_type": 2,
                "status_name": "Por Incidencia",
                "status_description": "Te muestra en estatus. Por Incidencia"
            },
            {
                "status_Id": 3,
                "status_type": 3,
                "status_name": "Día de descanso",
                "status_description": "Muestra que no estas laborando ya que estas en tu día de descanso"
            },
            {
                "status_Id": 4,
                "status_type": 4,
                "status_name": "Mix",
                "status_description": "Muestra que realizas actividades diversas en sala"
            },
            {
                "status_Id": 5,
                "status_type": 5,
                "status_name": "Vacaciones",
                "status_description": "Te muestra que estas tomando tu periodo vacacional"
            },
            {
                "status_Id": 6,
                "status_type": 6,
                "status_name": "Incapacidad",
                "status_description": "Te muestra en estatus por incidencia"
            }
        ]
    """.trimIndent()
}