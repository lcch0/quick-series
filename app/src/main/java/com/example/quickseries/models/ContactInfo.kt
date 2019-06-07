package com.example.quickseries.models

import android.os.Parcel
import android.os.Parcelable

class ContactInfo() : Parcelable
{
    var website = ""
    var email = ""
    var phoneNumber = ""
    var faxNumber = ""
    var tollFree = ""

    constructor(parcel: Parcel) : this()
    {
        website = parcel.readString() ?: ""
        email = parcel.readString() ?: ""
        phoneNumber = parcel.readString() ?: ""
        faxNumber = parcel.readString() ?: ""
        tollFree = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(website)
        parcel.writeString(email)
        parcel.writeString(phoneNumber)
        parcel.writeString(faxNumber)
        parcel.writeString(tollFree)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactInfo>
    {
        override fun createFromParcel(parcel: Parcel): ContactInfo
        {
            return ContactInfo(parcel)
        }

        override fun newArray(size: Int): Array<ContactInfo?>
        {
            return arrayOfNulls(size)
        }
    }
}
