package com.samnn.contactapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.samnn.contactapp.R
import com.samnn.contactapp.models.Contact
import com.samnn.contactapp.models.ContactLiveModel


class AddContactFragment : Fragment() {

    private val sharedViewModel: ContactLiveModel by activityViewModels()

    lateinit var firstChar: TextView
    lateinit var name: EditText
    lateinit var phone: EditText
    lateinit var email: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.contact_name_show)
        phone = view.findViewById(R.id.phone_number)
        email = view.findViewById(R.id.email_show)

        firstChar = view.findViewById(R.id.avatar_name)
        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) firstChar.text = s.subSequence(0..0)
                else firstChar.text = ""
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        view.findViewById<Button>(R.id.add_contact_button).setOnClickListener {
            if (!isEmpty()) sharedViewModel.add(
                Contact(
                    String(
                        CharArray(100) { (0..9).random().digitToChar() }),
                    name.text.toString(),
                    email.text.toString(),
                    phone.text.toString()
                )
            )
            findNavController().popBackStack()
        }
    }

    private fun isEmpty(): Boolean = name.text.isEmpty() || (email.text.isEmpty() && phone.text.isEmpty())
}