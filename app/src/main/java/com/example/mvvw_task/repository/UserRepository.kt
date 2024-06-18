package com.example.crud_assignments.repository





interface UserRepository {
    fun signUpUser(user: User, password: String, callback: (Boolean, String?) -> Unit)

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit)

    fun getUser(userId: String, callback: (User?, Boolean, String?) -> Unit)

    fun updateUser(userId: String, data: MutableMap<String, Any>?, callback: (Boolean, String?) -> Unit)

    fun deleteUser(userId: String, callback: (Boolean, String?) -> Unit)
}
