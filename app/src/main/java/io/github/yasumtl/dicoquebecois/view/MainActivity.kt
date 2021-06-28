package io.github.yasumtl.dicoquebecois.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import io.github.yasumtl.dicoquebecois.R
import io.github.yasumtl.dicoquebecois.viewmodel.ExpressionListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("MainActivity", "Called ViewModelProvider.get")
        val viewModel: ExpressionListViewModel = ViewModelProvider(this).get(ExpressionListViewModel::class.java)
        val expresQC = ArrayList<String>()
        val expresFR = ArrayList<String>()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, expresQC)
        listView.adapter = adapter

        viewModel.expressionListLiveData.observe(this, { newExpression ->
            //update UI
            if (newExpression != null)
            {
                for (oneEntry in newExpression.feed.entry){
                    val phraseQC = oneEntry.expressionQC.phraseQC
                    val phraseFR = oneEntry.expressionFR.phraseFR
                    phraseQC.let { expresQC.add(it) }
                    phraseFR.let { expresFR.add(it) }
                }

                adapter.notifyDataSetChanged()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Performs search when user hit the search button on the keyboard
//                if (expresQC.contains(query)) {
//                    adapter.filter.filter(query)
//                } else {
//                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_SHORT).show()
//                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Start filtering the list as user start entering the characters
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}