package com.example.myapplication

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication.arch.BurnerListInteractor
import com.example.myapplication.arch.BurnerListRepository
import com.example.myapplication.arch.BurnerListViewModel
import com.example.myapplication.arch.impl.BurnerListDataModelConverter
import com.example.myapplication.arch.impl.BurnerListInteractorImpl
import com.example.myapplication.arch.impl.BurnerListReadableModelCreator
import com.example.myapplication.arch.impl.BurnerListRepositoryImpl
import com.example.myapplication.arch.impl.BurnerListViewModelImpl
import com.example.myapplication.arch.impl.BurnerListWritableModelCreator

class MainActivity : AppCompatActivity() {

    private val interactor: BurnerListInteractor = BurnerListInteractorImpl(
        repository = BurnerListRepositoryImpl(),
        converter = BurnerListDataModelConverter(),
        writableCreator = BurnerListWritableModelCreator(),
        readableCreator = BurnerListReadableModelCreator()
    )

    private val viewModel: BurnerListViewModel = BurnerListViewModelImpl(
        interactor
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel.onAttached()

        var toggle = false
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            viewModel.onColorChange( if (toggle) 10 else 12)
            toggle = !toggle
        }

        viewModel.burnerColor.observeForever { color ->
            Snackbar.make(findViewById<FloatingActionButton>(R.id.fab), "Color changed to $color", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}