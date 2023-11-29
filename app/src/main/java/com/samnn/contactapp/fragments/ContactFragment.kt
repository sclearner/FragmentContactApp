package com.samnn.contactapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.samnn.contactapp.R

class ContactFragment : Fragment() {

    lateinit var firstChar: TextView
    lateinit var phone: TextView
    lateinit var email: TextView
    lateinit var name: TextView
    lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstChar = view.findViewById(R.id.avatar_name)
        phone = view.findViewById(R.id.phone_number)
        email = view.findViewById(R.id.email_show)
        name = view.findViewById(R.id.contact_name_show)

        phone.text = arguments?.getString("phone") ?: "No phone available"
        email.text = arguments?.getString("email") ?: "No email"
        name.text = arguments?.getString("name") ?: "Unknown"
        firstChar.text = name.text.subSequence(0..0)
    }
}