package com.example.quickseries.models

import android.os.Parcel
import android.os.Parcelable
import com.example.quickseries.intefaces.data.IModel
import java.lang.StringBuilder

class ResourceDetails() : IModel, Parcelable
{
    var title = Resource.DEFAULT_NAME
    var photo: String? = null
    var address: MutableList<Address>? = null
    var contactInfo: ContactInfo? = null
    var socialMedia: SocialMedia? = null

    fun getAddressString(): String
    {
        val local = address ?: return ""
        val strBld = StringBuilder()
        for (a in local)
        {
            strBld.appendln(a.addressLine1)
            strBld.appendln(a.city)
            strBld.appendln(a.country)
            strBld.appendln(a.state)
            strBld.appendln(a.zipCode)
        }

        return strBld.toString()
    }

    fun getContactsString(): String
    {
        val local = contactInfo ?: return ""
        val strBld = StringBuilder()

        if(local.phoneNumber.isNotEmpty())
        {
            strBld.appendln("Phone number")
            strBld.appendln(local.phoneNumber)
        }

        if(local.email.isNotEmpty())
        {
            strBld.appendln("E-mail")
            strBld.appendln(local.email)
        }

        if(local.website.isNotEmpty())
        {
            strBld.appendln("Web site")
            strBld.appendln(local.website)
        }

        return strBld.toString()
    }

    constructor(parcel: Parcel) : this()
    {
        title = parcel.readString() ?: Resource.DEFAULT_NAME
        photo = parcel.readString()
        address?.let {
            parcel.readTypedList(it, Address.CREATOR)
        }

        contactInfo = parcel.readParcelable(ContactInfo::class.java.classLoader)
        socialMedia = parcel.readParcelable(SocialMedia::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeString(title)
        parcel.writeString(photo)
        parcel.writeTypedList(address)
        parcel.writeParcelable(contactInfo, flags)
        parcel.writeParcelable(socialMedia, flags)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResourceDetails>
    {
        override fun createFromParcel(parcel: Parcel): ResourceDetails
        {
            return ResourceDetails(parcel)
        }

        override fun newArray(size: Int): Array<ResourceDetails?>
        {
            return arrayOfNulls(size)
        }
    }

}