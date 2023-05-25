package com.zitro.games.ticket.presentation.status.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.zitro.games.ticket.presentation.status.ZGPTRegionListModel
import com.zitro.games.util.common.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ZGPCTicketRegionDropDown(
    modifier: Modifier = Modifier,
    data: List<ZGPTRegionListModel>,
    status: MutableState<ZGPTRegionListModel>,
    onClick: (Int?) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = status.value.regionName ?: "",
            modifier = Modifier.menuAnchor(),
            onValueChange = {
                status.value = status.value.copy(regionName = it)
            },
            label = {
                Text(ctx.getString(R.string.zgc_screen_ticket_label_region))
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            //modifier = Modifier.exposedDropdownSize()
        ) {
            val list = if (!status.value.regionName.isNullOrEmpty()){
                data.filter {
                    (it.regionName
                        ?.lowercase(Locale.ROOT)
                        ?.contains(
                            status.value.regionName?.lowercase(Locale.ROOT)?: "") ?: ""
                            ) as? Boolean ?: false
                }
            } else data

            list.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(text = selectionOption.regionName ?: "")
                        }
                    },
                    onClick = {
                        status.value = selectionOption
                        keyboardController?.hide()
                        expanded = false
                        onClick.invoke(status.value.regionId)
                    }
                )
            }
        }
    }
}