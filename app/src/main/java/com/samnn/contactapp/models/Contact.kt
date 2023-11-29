package com.samnn.contactapp.models

import android.os.Bundle

data class Contact(val id: String, val name: String, val email: String, val phone: String) {
    fun toBundle() : Bundle {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("name", name)
        bundle.putString("email", email)
        bundle.putString("phone", phone)
        return bundle
    }
}
