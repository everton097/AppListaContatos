package com.example.listascomrolagem.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.listascomrolagem.R
import com.example.listascomrolagem.data.createContactsList
import com.example.listascomrolagem.models.Contact
import com.example.listascomrolagem.ui.views.AppScreens
import com.example.listascomrolagem.ui.views.AppUiState
import com.example.listascomrolagem.ui.views.ContactListUiState
import com.example.listascomrolagem.ui.views.InsertFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContactListViewModel : ViewModel() {

    private val _contactListUiState: MutableStateFlow<ContactListUiState> =
        MutableStateFlow(ContactListUiState(createContactsList()))

    val contactListUiState: StateFlow<ContactListUiState> =
        _contactListUiState.asStateFlow()
    private val _appUiState: MutableStateFlow<AppUiState> =
        MutableStateFlow(AppUiState())

    private val _insertFormUiState: MutableStateFlow<InsertFormUiState> =
            MutableStateFlow(InsertFormUiState())
    val insertFormUiState: StateFlow<InsertFormUiState> =
        _insertFormUiState.asStateFlow()

    val appUiState: StateFlow<AppUiState> =
        _appUiState.asStateFlow()

    private var contactToEdit: Contact = Contact()
    private var editContact: Boolean = false

    fun navigate(navController: NavController) {
        if (_appUiState.value.title == R.string.contact_list) {
            _appUiState.update { currentState ->
                currentState.copy(
                    title = R.string.insert_contact,
                    fabIcon = R.drawable.baseline_check_24,
                    iconContentDescription = R.string.confirm,
                )
            }
            navController.navigate(AppScreens.InsertContact.name)
        } else {
            val contacts: MutableList<Contact> = _contactListUiState.value.contactList.toMutableList()
            if (editContact){
                val pos = contacts.indexOf(contactToEdit)
                contacts.removeAt(pos)
                    contacts.add(pos, Contact(
                        _insertFormUiState.value.picture,
                        _insertFormUiState.value.name,
                        _insertFormUiState.value.status,
                    )
                )
                contactToEdit = Contact()
                editContact = false
            }else{
                contacts.add(Contact(
                        picture = _insertFormUiState.value.picture,
                        name = _insertFormUiState.value.name,
                        status =  _insertFormUiState.value.status,
                    )
                )
            }
            _contactListUiState.update { currentState ->
                currentState.copy(
                    contactList = contacts.toList()
                )
            }
            _insertFormUiState.update {
                InsertFormUiState()
            }
            _appUiState.update {
                AppUiState()
            }
            navController.navigate(AppScreens.ContactList.name){
                popUpTo(AppScreens.ContactList.name){
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController){
        _appUiState.update {
            AppUiState()
        }
        navController.popBackStack()
    }

    fun deletarContato(contact: Contact) {
        val contacts: MutableList<Contact> = _contactListUiState.value.contactList.toMutableList()
        contacts.remove(contact)
        _contactListUiState.value = _contactListUiState.value.copy(
            contactList = contacts.toList()
        )
    }

    fun onEditContact(contact: Contact, navController: NavController){
        editContact= true
        contactToEdit = contact
        _insertFormUiState.update { currentState ->
            currentState.copy(
                picture = contactToEdit.picture,
                name = contactToEdit.name,
                status = contactToEdit.status,
            )
        }
        _appUiState.update { currentState ->
            currentState.copy(
                title = R.string.edit_contact,
                fabIcon = R.drawable.baseline_check_24,
                iconContentDescription = R.string.confirm,
            )
        }
        navController.navigate(route = AppScreens.InsertContact.name)
    }

    fun onPictureChage(@DrawableRes picture:Int){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                picture = picture
            )
        }
    }
    fun onNameChage(name:String){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }
    fun onStatusChange(status:String){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                status = status
            )
        }
    }
}