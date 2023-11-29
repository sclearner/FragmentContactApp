package com.samnn.contactapp.interfaces

import com.samnn.contactapp.models.Contact

interface ItemClickListener {
    fun onItemClick(item: Contact)
}