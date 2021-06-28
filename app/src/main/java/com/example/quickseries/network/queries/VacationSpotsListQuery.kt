package com.example.quickseries.network.queries

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quickseries.intefaces.network.IQuery
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.network.json.vacationspots.VacationSpot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class VacationSpotsListQuery : IQuery<List<VacationSpot>>
{
    companion object
    {
        const val FILE_NAME = "vacation-spot"
        const val ID = "vacation-spots"
    }

    private val url = "https://raw.githubusercontent.com/quickseries/mobile-test/master/data/${FILE_NAME}.json"

    override var onSuccess: ((List<VacationSpot>) -> Unit)? = null

    override fun request(app: IAppContext)
    {
        val queue = Volley.newRequestQueue(app.getContextObject())

        val stringReq = StringRequest(Request.Method.GET, url, {
            try
            {
                parseJson(it)?.let{ d -> onSuccess?.invoke(d) }
            }
            catch (e: Exception)
            {
                Log.e("VacationSpotsListQuery", e.toString())
            }
        }, {
            Log.e("VacationSpotsListQuery", it?.message?:"")
        })

        queue.add(stringReq)
    }

    private fun parseJson(it: String?): List<VacationSpot>?
    {
        val listType = object : TypeToken<List<VacationSpot>>(){}.type
        return Gson().fromJson<List<VacationSpot>>(it, listType)
    }

    fun testRequest(app: IAppContext): List<VacationSpot>
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

        return listOf()
    }
}