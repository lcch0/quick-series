package com.example.quickseries.network.queries

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quickseries.intefaces.network.IQuery
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.network.json.restorants.Restorant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class RestaurantListQuery : IQuery<List<Restorant>>
{
    companion object
    {
        const val FILE_NAME = "restaurants"
        const val ID = FILE_NAME
    }

    private val url = "https://raw.githubusercontent.com/quickseries/mobile-test/master/data/$FILE_NAME.json"

    override var onSuccess: ((List<Restorant>) -> Unit)? = null

    override fun request(app: IAppContext)
    {
        val queue = Volley.newRequestQueue(app.getContextObject())

        val stringReq = StringRequest(Request.Method.GET, url, {
            try
            {
                parseJson(it)?.let{ d -> onSuccess?.invoke(d)}
            }
            catch (e: Exception)
            {
                Log.e("RestaurantListQuery", e.toString())
            }
        }, {
            Log.e("RestaurantListQuery", it?.message?:"")
        })

        queue.add(stringReq)
    }

    private fun parseJson(it: String?): List<Restorant>?
    {
        val listType = object : TypeToken<List<Restorant>>(){}.type
        return Gson().fromJson<List<Restorant>>(it, listType)
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    fun testRequest(app: IAppContext): List<Restorant>
    {
        var ex: java.lang.Exception? = null
        try
        {
            val queue = Volley.newRequestQueue(app.getContextObject())
            val reqFuture = RequestFuture.newFuture<String>()
            val stringReq = StringRequest(Request.Method.GET, url, reqFuture, reqFuture)
            queue.add(stringReq)


            try
            {
                val response = reqFuture.get(10, TimeUnit.SECONDS) // this will block
                return parseJson(response) ?: listOf()
            }
            catch (e: InterruptedException)
            {
                // exception handling
                ex = e
            }
            catch (e: ExecutionException)
            {
                // exception handling
                ex = e
            }
        }
        catch (e: Exception)
        {
            ex = e
        }

        return mutableListOf()
    }
}