package com.example.chatmekotlin.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatmekotlin.Constants.AppConstants
import com.example.chatmekotlin.UserModel
import com.example.chatmekotlin.Util.AppUtil
import com.google.firebase.database.*

class AppRepo {

    private var liveData: MutableLiveData<UserModel>? = null
    private lateinit var databaseReference: DatabaseReference
    private val appUtil = AppUtil()


    object StaticFunction {
        private var instance: AppRepo? = null
        fun getInstance(): AppRepo {
            if (instance == null)
                instance = AppRepo()

            return instance!!
        }
    }

    fun getUser(): LiveData<UserModel> {

        if (liveData == null)
            liveData = MutableLiveData()
        databaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(appUtil.getUID()!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userModel = snapshot.getValue(UserModel::class.java)
                    liveData!!.postValue(userModel)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return liveData!!
    }

    fun updateName(userName: String?) {


        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(appUtil.getUID()!!)

        val map = mapOf<String, Any>("name" to userName!!)
        databaseReference.updateChildren(map)

    }

    fun updateStatus(status: String) {

        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(appUtil.getUID()!!)

        val map = mapOf<String, Any>("status" to status)
        databaseReference.updateChildren(map)

    }

    fun updateImage(imagePath: String) {
        val databaseReference =
            FirebaseDatabase.getInstance().getReference("Users").child(appUtil.getUID()!!)

        val map = mapOf<String, Any>("image" to imagePath)
        databaseReference.updateChildren(map)
    }

}

