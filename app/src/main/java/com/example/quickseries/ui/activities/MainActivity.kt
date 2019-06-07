package com.example.quickseries.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.quickseries.R
import com.example.quickseries.intefaces.views.ISortCallback
import com.example.quickseries.intefaces.views.SortDirection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ISortCallback
{
    private var sortDirection = SortDirection.Asc
    private lateinit var navController: NavController

    override var onSort: ((SortDirection) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        val id = item?.itemId ?: return false

        if(id != R.id.sortBtn)
            return false

        sortDirection = when (sortDirection)
        {
            SortDirection.Asc -> SortDirection.Desc
            SortDirection.Desc -> SortDirection.Asc
        }

        onSort?.invoke(sortDirection)

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean
    {
        return  if(::navController.isInitialized)
                    navController.navigateUp()
                else
                    super.onSupportNavigateUp()
    }
}
