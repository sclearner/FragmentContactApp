package com.samnn.contactapp.fragments

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samnn.contactapp.R
import com.samnn.contactapp.adapters.ListContactAdapter
import com.samnn.contactapp.models.ContactLiveModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    lateinit var contactListView: RecyclerView

    private val sharedViewModel: ContactLiveModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.popup)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_contact -> {
                    findNavController().navigate(R.id.action_mainFragment_to_addContactFragment)
                    true
                }
                else -> false
            }
        }
        //sharedViewModel.set(contacts)

        contactListView = view.findViewById(R.id.contact_list)
        contactListView.layoutManager = LinearLayoutManager(requireContext())
        contactListView.adapter = ListContactAdapter(this, requireContext(), sharedViewModel)

        registerForContextMenu(contactListView)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.hold_action, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.call -> {
                true
            }
            R.id.message -> {
                true
            }
            R.id.email -> true
            else -> super.onContextItemSelected(item)
        }
    }
}