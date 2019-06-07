package com.example.quickseries.ui.fragments.resourcedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickseries.QuickSeriesApp
import com.example.quickseries.intefaces.data.IRepository
import com.example.quickseries.models.ResourceDetails
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log


class ResourceDetailsViewViewModel(repository: IRepository<ResourceDetails>) : ViewModel()
{
    fun call(s: Any?)
    {
        //call here
    }

    fun locate(tag: Any?)
    {
        try
        {
            val data = tag as? Pair<Double, Double> ?: return

            val intent = Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/search/?api=1&query=${data.first},${data.second}"))
            QuickSeriesApp.appContext.getContextObject().startActivity(intent)
        }
        catch (e: Exception)
        {
            Log.e("Location", "")
        }
    }

    val key = "resourceDetails"

    fun saveTo(outState: Bundle?)
    {
        outState?.putParcelable(key, data.value)
    }

    fun loadFrom(inState: Bundle?): Boolean
    {
        if(inState != null)
        {
            val bundleData = inState.getParcelable<ResourceDetails>(key)
            if(bundleData != null)
            {
                data.value = bundleData
                return true
            }
        }

        return false;
    }

    var data: MutableLiveData<ResourceDetails> = MutableLiveData()

    init
    {
        repository.onModelLoaded = { data.value = it }
        repository.loadModel(QuickSeriesApp.appContext)
    }
}
