package com.example.quickseries.network.queries

import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quickseries.intefaces.network.IQuery
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.network.json.Categories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryResourceQuery(private val categoryId: String) : IQuery<Categories>
{
    override var onSuccess: ((Categories) -> Unit)? = null
    private val url = "https://raw.githubusercontent.com/quickseries/mobile-test/master/data/categories.json"

    override fun request(app: IAppContext)
    {
        val queue = Volley.newRequestQueue(app.getContextObject())

        val stringReq = StringRequest(Request.Method.GET, url, Response.Listener<String>
        {
            try
            {
                val listType = object : TypeToken<List<Categories>>() { }.type
                val data = Gson().fromJson<List<Categories>>(it, listType)

                val category = data.find { c -> c.eid == categoryId }

                if(category != null)
                    onSuccess?.invoke(category)
            }
            catch (e: Exception)
            {
                Log.e("CategoryListQuery", e.toString())
            }
        },
                                      Response.ErrorListener
                                      {
                                          Log.e("CategoryListQuery", it?.message)
                                      })

        queue.add(stringReq)
    }
}