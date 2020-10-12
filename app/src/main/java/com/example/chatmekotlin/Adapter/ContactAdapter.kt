package com.example.chatmekotlin.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmekotlin.MessageActivity
import com.example.chatmekotlin.Activities.UserInfoActivity
import com.example.chatmekotlin.UserModel
import com.example.chatmekotlin.databinding.ContactItemLayoutBinding
import java.util.*
import kotlin.collections.ArrayList

class ContactAdapter(private var appContacts: ArrayList<UserModel>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>(), Filterable {

    private var allContact: ArrayList<UserModel> = appContacts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contactItemLayoutBinding =
            ContactItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(contactItemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel = allContact[position]
        holder.contactItemLayoutBinding.userModel = userModel

        holder.contactItemLayoutBinding.imgContact.setOnClickListener {
            val intent = Intent(it.context, UserInfoActivity::class.java)
            intent.putExtra("userId", userModel.uid)
            it.context.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MessageActivity::class.java)
            intent.putExtra("hisId", userModel.uid)
            intent.putExtra("hisImage", userModel.image)
            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return allContact.size
    }

    class ViewHolder(val contactItemLayoutBinding: ContactItemLayoutBinding) :
        RecyclerView.ViewHolder(contactItemLayoutBinding.root) {

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchContent = constraint.toString()
                if (searchContent.isEmpty())
                    allContact = appContacts
                else {

                    val filterContact = ArrayList<UserModel>()
                    for (userModel in appContacts) {

                        if (userModel.name.toLowerCase(Locale.ROOT).trim()
                                .contains(searchContent.toLowerCase(Locale.ROOT).trim())
                        )
                            filterContact.add(userModel)
                    }
                    allContact = filterContact
                }

                val filterResults = FilterResults()
                filterResults.values = allContact
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                allContact = results?.values as ArrayList<UserModel>
                notifyDataSetChanged()

            }
        }
    }

}