package com.example.crud_assignments

import com.example.crud_assignments.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User

class UserRepositoryImpl : UserRepository {

    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val ref: DatabaseReference = firebaseDatabase.reference.child("users")
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun signUpUser(user: User, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid ?: return@addOnCompleteListener
                    user.id = userId
                    ref.child(userId).setValue(user)
                        .addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                callback(true, "User signed up successfully")
                            } else {
                                callback(false, dbTask.exception?.message)
                            }
                        }
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    override fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "User logged in successfully")
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    override fun getAllUsers(callback: (List<User>?, Boolean, String?) -> Unit) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = mutableListOf<User>()
                for (eachData in snapshot.children) {
                    val user = eachData.getValue(User::class.java)
                    if (user != null) {
                        userList.add(user)
                    }
                }
                callback(userList, true, "Data successfully retrieved")
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null, false, "Unable to fetch ${error.message}")
            }
        })
    }

    override fun updateUser(userId: String, data: MutableMap<String, Any>?, callback: (Boolean, String?) -> Unit) {
        data?.let {
            ref.child(userId).updateChildren(data).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Your data has been updated")
                } else {
                    callback(false, "Unable to update data")
                }
            }
        }
    }

    override fun deleteUser(userId: String, callback: (Boolean, String?) -> Unit) {
        ref.child(userId).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, "User has been deleted")
            } else {
                callback(false, "Unable to delete")
            }
        }
    }
}
