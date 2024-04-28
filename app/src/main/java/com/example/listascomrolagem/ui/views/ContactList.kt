package com.example.listascomrolagem.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listascomrolagem.R
import com.example.listascomrolagem.models.Contact
import com.example.listascomrolagem.ui.theme.ListasComRolagemTheme
import com.example.listascomrolagem.viewmodels.ContactListViewModel

@Composable
fun ContactList(
    viewModel: ContactListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.contactListUiState.collectAsState()

    LazyColumn {
        items(uiState.contactList) { contact ->
            ContactCard(
                contact = contact,
                onDelete = viewModel::deletarContato,
                onEditContact = {
                    viewModel.onEditContact(
                        contact = contact,
                        navController = navController,
                    )
                },
                modifier = modifier
            )
        }
    }

}

@Composable
fun ContactCard(
    contact: Contact,
    onDelete: (Contact) -> Unit,
    onEditContact: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(2.dp)
        .clickable {
            onEditContact()
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = contact.picture),
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
                    .padding(start = 4.dp, end = 4.dp)
            )
            Column {
                Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(
                    text = contact.status,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = modifier.width(260.dp)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "delete",
                modifier
                    .padding(end = 8.dp)
                    .clickable { onDelete(contact) })

        }

    }

}
/*
@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ListasComRolagemTheme {
        ContactList(viewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ListasComRolagemTheme {
        ContactCard(contact = Contact(
            picture = R.drawable.baseline_face_24,
            name = "Yoda",
            status = "May the force be with you."
        ), {})
    }
}*/