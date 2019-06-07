package com.example.quickseries.models

import android.os.Parcel
import android.os.Parcelable

class Address() : Parcelable
{
    var addressLine1: String = ""
    var label = ""
    var zipCode = ""
    var city = ""
    var state = ""
    var country = ""
    var geoCoordinates: Pair<Double, Double>? = null

    constructor(parcel: Parcel) : this()
    {
        addressLine1 = parcel.readString() ?: ""
        label = parcel.readString() ?: ""
        zipCode = parcel.readString() ?: ""
        city = parcel.readString() ?: ""
        state = parcel.readString() ?: ""
        country = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(addressLine1)
        parcel.writeString(label)
        parcel.writeString(zipCode)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(country)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address>
    {
        override fun createFromParcel(parcel: Parcel): Address
        {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?>
        {
            return arrayOfNulls(size)
        }
    }
}
