package io.github.yasumtl.dicoquebecois.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.github.yasumtl.dicoquebecois.R
import kotlinx.android.synthetic.main.activity_meaning.*

class Meaning : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning)

        setTextViews()
        setBackButton()
    }

    private fun setTextViews(){
        val bundle: Bundle? = intent.extras
        indexTV.text = bundle?.getInt("index").toString()
        tvExpressionQc.text = bundle?.getString("expression")
        tvMeaningFr.text = bundle?.getString("meaning")
    }

    private fun setBackButton(){
        btn_back.setOnClickListener {
            backToMain(it)
        }
    }

    private fun backToMain(view: View){
        finish()
    }
}