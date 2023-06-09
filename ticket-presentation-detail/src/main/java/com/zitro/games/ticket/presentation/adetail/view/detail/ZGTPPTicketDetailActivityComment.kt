package com.zitro.games.ticket.presentation.adetail.view.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zitro.games.presentation.common.navigation.input.ticket.CPTicketDetailInput
import com.zitro.games.ticket.presentation.adetail.ZGTPPTicketDetailViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun ZGTPPTicketDetailComment(
    viewModel: ZGTPPTicketDetailViewModel,
    catalogInput: CPTicketDetailInput,
    navController: NavController,
){
    var show by remember {
        mutableStateOf(false)
    }


    var comment by remember { mutableStateOf(
           "Marcos Francisco Pineda Simons: 12 mar. 2023 22:41 se require cambio\n" +
           "Marcos Francisco Pineda Simons: 12 mar. 2023 09:44 terminal no cuenta con QR\n" +
           "Marcos Francisco Pineda Simons: 12 mar. 2023 22:44 Se solicita QR por correo\n" +
           "Marcos Francisco Pineda Simons: 12 mar. 2023 12:48  monitor no cuenta con el eliminador, se solicita a cc 351015, eliminador de corriente black o blu\n" +
           "Jonathan Andrade: 13 mar. 2023 14:07 Pedido 206550\n" +
           "Rodrigo Macias (Técnico): 13 mar. 2023 18:25 Se libera pedido\n"
        )
    }

    var sedComment by remember { mutableStateOf("") }

    Tab(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(50))
            .background(
                MaterialTheme.colorScheme.primary
            ),
        selected = true,
        onClick = {
            show = !show
        },
        text = {
            Text(
                text = "Comentarios",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    )

    val date = Date(System.currentTimeMillis())
    val format = SimpleDateFormat("dd MMM. yyyy HH:mm", Locale.ENGLISH)

    if (show){
        Column(
            Modifier
                .padding(10.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (box, textField, button) = createRefs()

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .constrainAs(box) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .padding(bottom = 20.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                ){
                    Text(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        text = comment.trimIndent()
                    )
                }

                TextField(
                    modifier = Modifier
                        .constrainAs(textField) {
                            top.linkTo(box.bottom)
                            start.linkTo(parent.start, margin = 0.dp)
                            end.linkTo(button.start, margin = 0.dp)
                        }
                        .fillMaxWidth(.7f)
                        .padding(bottom = 10.dp),
                    value = sedComment,
                    onValueChange = {
                        sedComment = it
                    },
                    label = { Text(text = "Comentarios")}
                )

                Button(
                    modifier = Modifier
                        .constrainAs(button) {
                            top.linkTo(box.bottom)
                            start.linkTo(textField.end, margin = 0.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                        }
                        .fillMaxWidth(.3f)
                        .padding(start = 5.dp, end = 10.dp, bottom = 10.dp)
                    ,
                    onClick = {
                        comment = "${comment}Admin: ${format.format(date)} $sedComment"
                        sedComment = ""
                    }
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Default.Send),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}