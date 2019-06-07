package com.example.quickseries

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.quickseries.intefaces.views.IAppContext
import com.example.quickseries.network.queries.CategoryListQuery
import com.example.quickseries.network.queries.RestaurantListQuery
import com.example.quickseries.network.queries.VacationSpotsListQuery

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class JsonQueryTests
{
    class testContext : IAppContext
    {
        override fun getContextObject(): Context
        {
            return InstrumentationRegistry.getTargetContext()
        }

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.quickseries", appContext.packageName)
    }

    @Test
    fun CategoryQueryTest()
    {
        val app = testContext()
        val query = CategoryListQuery()
        val list = query.testRequest(app)

        assert(true)
    }

    @Test
    fun RestoQueryTest()
    {
        val app = testContext()
        val query = RestaurantListQuery()
        val list = query.testRequest(app)

        assert(list.isNotEmpty())
    }

    @Test
    fun VacationSpotsQueryTest()
    {
        val app = testContext()
        val query = VacationSpotsListQuery()
        val list = query.testRequest(app)

        assert(list.isNotEmpty())
    }
}
