package com.samnn.contactapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactLiveModel(): ViewModel() {
    private val _dataList = MutableLiveData<ArrayList<Contact>>(arrayListOf())
    val list: LiveData<ArrayList<Contact>> = _dataList

    fun set(contacts: ArrayList<Contact>) {
        _dataList.value = contacts
    }

    fun add(contact: Contact) {
        _dataList.value?.add(contact)
    }
}