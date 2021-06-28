package com.example.quickseries.network.queries

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quickseries.intefaces.network.IQuery
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.network.json.Categories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit


class CategoryListQuery : IQuery<List<Categories>>
{
    private val url = "https://raw.githubusercontent.com/quickseries/mobile-test/master/data/categories.json"

    override var onSuccess: ((List<Categories>) -> Unit)? = null

    override fun request(app: IAppContext)
    {
        val queue = Volley.newRequestQueue(app.getContextObject())

        val stringReq = StringRequest(Request.Method.GET, url, {
            try
            {
                val listType = object : TypeToken<List<Categories>>() { }.type
                val data = Gson().fromJson<List<Categories>>(it, listType)

                onSuccess?.invoke(data)
            }
            catch (e: Exception)
            {
                Log.e("CategoryListQuery", e.toString())
            }
        }, {
            Log.e("CategoryListQuery", it?.message?:"")
        })

        queue.add(stringReq)
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    fun testRequest(app: IAppContext): List<Categories>
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
                val listType = object : TypeToken<List<Categories>>() { }.type
                return Gson().fromJson<List<Categories>>(response, listType)
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