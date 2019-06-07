package com.example.quickseries

import android.app.Application
import android.content.Context
import com.example.quickseries.intefaces.views.IAppContext

class QuickSeriesApp : Application(), IAppContext
{
    override fun getContextObject(): Context
    {
        return applicationContext
    }

    companion object
    {
        //this is kinda risky, but if someone wants to get the app context before the app is created, well, let's hav an exception
        lateinit var appContext: IAppContext
    }

    init
    {
        appContext = this
    }


}