package com.zitro.games.ticket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zitro.games.presentation.common.navigation.CPDataUserApiModel
import com.zitro.games.presentation.common.navigation.NavRoutes
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketRegistrationInput
import com.zitro.games.presentation.common.ui.theme.CleanAppTheme
import com.zitro.games.ticket.presentation.adetail.view.detail.ZGTPPTicketDetailScreen
import com.zitro.games.ticket.presentation.registration.ZGTPTicketRegistrationScreen
import com.zitro.games.ticket.presentation.technical.ZGTPPTicketTechnicalScreen
import com.zitro.games.ticket.presentation.technical.detail.ZGTPPTicketTechnicalDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanAppTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.AtServiceTicketRegistration.route) {
        /*composable(route = NavRoutes.AtServiceTicketPending.route) {
            ZGTPPTicketPendingScreen(
                hiltViewModel(),
                CPTicketPendingInput(
                    dataUser = CPDataUserApiModel(
                        userId = 0,
                        token = """bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7ImlkIjoxLCJ1c3VhcmlvIjoiQWRtaW5pc3RyYWRvciIsIm5vbWJyZSI6IkFkbWluIiwiYXBlbGxpZG8iOiIiLCJhcmVhIjoiYWRtaW5pc3RyYWRvciIsImRlcGFydGFtZW50byI6IkFkbWluaXN0cmFkb3IifSwiaWF0IjoxNjgwMDU4NzU0fQ.DFXy5KYAaOTuf0vHr3Z0frbRbHVxCb21nmSu-jFLzrY""".trimIndent()
                    )
                ), navController
            )
        }

        composable(route = NavRoutes.AtServiceTicketDetail.route) {
            ZGTPPTicketDetailScreen(
                hiltViewModel(),
                NavRoutes.AtServiceTicketDetail.fromEntry(it),
                navController
            )
        }*/

        composable(route = NavRoutes.AtServiceTicketRegistration.route) {
            ZGTPTicketRegistrationScreen(
                hiltViewModel(),
                CPTicketRegistrationInput(
                    dataUser = CPDataUserApiModel(
                        userId = 0,
                        token = """bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7ImlkIjoxLCJ1c3VhcmlvIjoiQWRtaW5pc3RyYWRvciIsIm5vbWJyZSI6IkFkbWluIiwiYXBlbGxpZG8iOiIiLCJhcmVhIjoiYWRtaW5pc3RyYWRvciIsImRlcGFydGFtZW50byI6IkFkbWluaXN0cmFkb3IifSwiaWF0IjoxNjgwMDU4NzU0fQ.DFXy5KYAaOTuf0vHr3Z0frbRbHVxCb21nmSu-jFLzrY""".trimIndent()
                    )
                ),
                navController
            )
        }

        composable(route = NavRoutes.AtServiceTicketTechnical.route) {
            ZGTPPTicketTechnicalScreen(
                hiltViewModel(),
                NavRoutes.AtServiceTicketTechnical.fromEntry(it),
                navController
            )
        }

        composable(route = NavRoutes.AtServiceTicketTechnicalDetail.route) {
            ZGTPPTicketTechnicalDetailScreen(
                hiltViewModel(),
                NavRoutes.AtServiceTicketTechnicalDetail.fromEntry(it),
                navController
            )
        }

        composable(route = NavRoutes.AtServiceTicketDetail.route) {
            ZGTPPTicketDetailScreen(
                hiltViewModel(),
                NavRoutes.AtServiceTicketDetail.fromEntry(it),
                navController
            )
        }

        /*composable(route = NavRoutes.AtServiceTicketStatus.route) {
            ZGTPStatusScreen(hiltViewModel(), CPTicketStatusInput(
                dataUser = CPDataUserApiModel(
                    userId = 0,
                    token = """bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7ImlkIjoxLCJ1c3VhcmlvIjoiQWRtaW5pc3RyYWRvciIsIm5vbWJyZSI6IkFkbWluIiwiYXBlbGxpZG8iOiIiLCJhcmVhIjoiYWRtaW5pc3RyYWRvciIsImRlcGFydGFtZW50byI6IkFkbWluaXN0cmFkb3IifSwiaWF0IjoxNjgwMDU4NzU0fQ.DFXy5KYAaOTuf0vHr3Z0frbRbHVxCb21nmSu-jFLzrY""".trimIndent()
                )
            ), navController)
        }*/
    }
}