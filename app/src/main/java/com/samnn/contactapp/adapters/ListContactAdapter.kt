package com.samnn.contactapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.samnn.contactapp.R
import com.samnn.contactapp.models.ContactLiveModel

class ListContactAdapter(val fragment: Fragment, val context: Context, val contacts: ContactLiveModel) : RecyclerView.Adapter<ListContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListContactAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.contact_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListContactAdapter.ViewHolder, position: Int) {
        val contact = contacts.list.value!![position]
        if (contact.name.isBlank()) return
        holder.avatar.text = contact.name.subSequence(0..0)
        holder.contact.text = contact.name
        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(R.id.action_mainFragment_to_contactFragment, contact.toBundle())
        }
    }

    override fun getItemCount() = contacts.list.value?.size ?: 0

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val avatar: TextView = itemView.findViewById(R.id.avatar_name)
        val contact: TextView = itemView.findViewById(R.id.contact_name)
        init {
            itemView.setOnLongClickListener {
                val menu = PopupMenu(itemView.context, it)
                menu.inflate(R.menu.hold_action)

                menu.setOnMenuItemClickListener {
                    item -> when(item.itemId) {
                    R.id.call -> {
                        Toast.makeText(itemView.context, "Call ${contact.text}", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.message -> {
                        Toast.makeText(itemView.context, "Message ${contact.text}", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.email -> {
                        Toast.makeText(itemView.context, "Mail ${contact.text}", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                    }
                }
                menu.show()
                true
            }
        }
    }

}