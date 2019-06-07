package com.example.quickseries.models

import android.os.Parcel
import android.os.Parcelable

class SocialMedia() : Parcelable
{
    var facebook: MutableList<String> = mutableListOf()
    var twitter: MutableList<String> = mutableListOf()
    var youtubeChannel: MutableList<String> = mutableListOf()

    constructor(parcel: Parcel) : this()
    {
        parcel.readStringList(facebook)
        parcel.readStringList(twitter)
        parcel.readStringList(youtubeChannel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeStringList(facebook)
        parcel.writeStringList(twitter)
        parcel.writeStringList(youtubeChannel)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SocialMedia>
    {
        override fun createFromParcel(parcel: Parcel): SocialMedia
        {
            return SocialMedia(parcel)
        }

        override fun newArray(size: Int): Array<SocialMedia?>
        {
            return arrayOfNulls(size)
        }
    }
}
