package com.example.crud_assignments.viewmodel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud_assignments.repository.UserRepository
import com.google.firebase.firestore.auth.User


class UserViewModel(val repository: UserRepository) : ViewModel() {

    fun signUpUser(user: User, password: String, callback: (Boolean, String?) -> Unit) {
        repository.signUpUser(user, password, callback)
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        repository.loginUser(email, password, callback)
    }

    fun updateUser(userId: String, data: MutableMap<String, Any>?, callback: (Boolean, String?) -> Unit) {
        repository.updateUser(userId, data, callback)
    }

    fun deleteUser(userId: String, callback: (Boolean, String?) -> Unit) {
        repository.deleteUser(userId, callback)
    }

    fun signInUser(email: Any, password: Any, any: Any) {

    }

    var _userList = MutableLiveData<List<User>?>()
    var userList = MutableLiveData<List<User>?>()
        get() = _userList

    var _loadingState = MutableLiveData<Boolean>()
    var loadingState = MutableLiveData<Boolean>()
        get() = _loadingState

//    fun fetchUsers() {
//        _loadingState.value = true
//        repository.getAllUsers { userList, success, message ->
//            if (userList != null) {
//                _loadingState.value = false
//                _userList.value = userList
//            }
//        }
//    }
}
