package com.example.crud_assignments

import android.os.Parcel
import android.os.Parcelable

data class usermodel(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var address: String = "",
    var password: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(address)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<usermodel> {
        override fun createFromParcel(parcel: Parcel): usermodel {
            return usermodel(parcel)
        }

        override fun newArray(size: Int): Array<usermodel?> {
            return arrayOfNulls(size)
        }
    }
}
