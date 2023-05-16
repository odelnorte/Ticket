package com.zitro.games.ticket.presentation.pending.view.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.zitro.games.presentation.common.ui.custom.dialog.ZGPCFilterDialog
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCMessageTypeButton
import com.zitro.games.presentation.common.ui.custom.model.button.ZGPCParserButton
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCFilterTypeDialog
import com.zitro.games.presentation.common.ui.custom.model.dialog.ZGPCParserDialog
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCFilterTypeDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCModelListDropDown
import com.zitro.games.presentation.common.ui.custom.model.dropdown.ZGPCParserDropDown
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCFilterTypeTextField
import com.zitro.games.presentation.common.ui.custom.model.textfield.ZGPCParserTextField

@Composable
fun ZGTPPTicketTechnicalFilterDialog(
    openFilter: MutableState<Boolean>
) {
    val ctx = LocalContext.current
    ZGPCFilterDialog(
        typeDialog = ZGPCParserDialog.dialog(
            ctx, ZGPCFilterTypeDialog.ZGPC_DIALOG_FILTER_LIST_PENDING_TECHNICAL
        ),
        btn =  ZGPCParserButton.dialogButton(
            ctx, listOf(
                ZGPCMessageTypeButton.ZGPC_BUTTON_CANCEL,
                ZGPCMessageTypeButton.ZGPC_BUTTON_ACCEPT
            )
        ),
        dropDowns = ZGPCParserDropDown.dialogDropDown(
            ctx, listOf(
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM to listOf(
                    ZGPCModelListDropDown(
                        id = 1,
                        content = "CALIENTE CULIACAN",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    ),
                    ZGPCModelListDropDown(
                        id = 2,
                        content = "CASINO EL DORADO",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    ),
                    ZGPCModelListDropDown(
                        id = 3,
                        content = "CROWN CITY NANOJOA VS",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_ROOM
                    )
                ),
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION to listOf(
                    ZGPCModelListDropDown(
                        id = 30,
                        content = "Alava",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 18,
                        content = "Granada",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 38,
                        content = "La Coruña",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    ),
                    ZGPCModelListDropDown(
                        id = 47,
                        content = "Zamora",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_REGION
                    )
                ),
                ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT to listOf(
                    ZGPCModelListDropDown(
                        id = 18,
                        content = "Gran Madrid Torrelodones",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    ),
                    ZGPCModelListDropDown(
                        id = 38,
                        content = "Admiral Casino San Roque ",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    ),
                    ZGPCModelListDropDown(
                        id = 47,
                        content = "Casino Barcelona",
                        description = "",
                        ZGPCFilterTypeDropDown.ZGPC_DROPDOWN_CLIENT
                    )
                )
            )
        ),
        textFields = ZGPCParserTextField.dialogTextField(
            ctx, listOf(
                ZGPCFilterTypeTextField.ZGPC_TEXT_FIELD_TICKET
            )
        ),
        openDialogCustom = openFilter
    ) {

    }
}