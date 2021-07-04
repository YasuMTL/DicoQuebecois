package io.github.yasumtl.dicoquebecois.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.yasumtl.dicoquebecois.model.Expression
import io.github.yasumtl.dicoquebecois.model.Repository
import java.lang.Exception
import java.lang.reflect.InvocationTargetException

class ExpressionListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = Repository.instance

    var expressionListLiveData: MutableLiveData<Expression> = MutableLiveData()

    init {
        loadExpressionList()
        Log.i("ExpressionListViewModel", "ExpressionListViewModel created!")
    }

    private fun loadExpressionList()
    {
        try
        {
            expressionListLiveData = repository.fetchExpressions()
        }
        catch (e: InvocationTargetException)
        {
            e.stackTrace
            Log.d("loadExpressionList", e.cause.toString())
        }
        catch (e: Exception)
        {
            e.stackTrace
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ExpressionListViewModel", "ExpressionListViewModel destroyed!")
    }
}