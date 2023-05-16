package com.zitro.games.ticket.presentation.technical.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketTechnicalDetailInput
import com.zitro.games.presentation.common.ui.custom.bar.ZGPCTopAppBar
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZGTPPTicketTechnicalDetailScreen(
    viewModel: ZGTPTicketTechnicalDetailViewModel,
    technicalInput: CPTicketTechnicalDetailInput,
    navController: NavController
){

    val openFilter = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ZGPCTopAppBar(
                "Detalle del Técnico",
            )
        }
    ) { contentPadding ->
        ConstraintLayout(
            modifier = Modifier.padding(contentPadding)
        ) {
            val (tab, list) = createRefs()
            Tab(
                modifier = Modifier
                    .constrainAs(tab) {
                        top.linkTo(parent.top)
                        bottom.linkTo(list.top)
                    }
                    .height(60.dp)
                    .padding(vertical = 5.dp, horizontal = 25.dp)
                    .fillMaxHeight(.1f)
                    .clip(RoundedCornerShape(50))
                    .background(
                        MaterialTheme.colorScheme.primary
                    ),
                selected = true,
                onClick = {

                },
                text = {
                    Text(
                        text = technicalInput.technical.name ?: "",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

            ConstraintLayout(Modifier
                .constrainAs(list) {
                    top.linkTo(tab.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxHeight(.9f)
            ) {
                val (list1, list2) = createRefs()

                ZGTPPTicketTechnicalProcessList(Modifier
                    .constrainAs(list1) {
                        top.linkTo(parent.top)
                        bottom.linkTo(list2.top)
                    }
                    .fillMaxHeight(.5f),
                    viewModel,
                    openFilter,
                    navController
                )

                ZGTPPTicketTechnicalRequestList(Modifier
                    .constrainAs(list2) {
                        top.linkTo(list1.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxHeight(.5f),
                    viewModel,
                    openFilter,
                    navController
                )

            }

        }
    }

    ZGTPPTicketTechnicalFilterDialog(openFilter)

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is ZGTPTicketTechnicalDetailUiSingleEvent.Navigate -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}
