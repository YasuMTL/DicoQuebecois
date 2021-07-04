package io.github.yasumtl.dicoquebecois.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//https://qiita.com/Tsutou/items/69a28ebbd69b69e51703#%E3%82%B5%E3%83%B3%E3%83%97%E3%83%AB
class Repository {
    //"https://spreadsheets.google.com/feeds/list/1iJaD8rGSLImmYuEfIHvvawNAK1Q6ckCdImJvz2Lw7QE/od6/public/values?alt=json"
    private val googleSheetUrl: String =
        "https://spreadsheets.google.com/feeds/list/1iJaD8rGSLImmYuEfIHvvawNAK1Q6ckCdImJvz2Lw7QE/od6/public/"

    private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(googleSheetUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val googleSheet:GoogleSheet = retrofit.create(GoogleSheet::class.java)

    val expressionListLiveData: MutableLiveData<Expression> = MutableLiveData()

    fun fetchExpressions(): MutableLiveData<Expression>
    {
        googleSheet.getAllExpressions().enqueue(object : Callback<Expression>
        {
            override fun onResponse(call: Call<Expression>, response: Response<Expression>) {
                expressionListLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Expression>, t: Throwable) {
                Log.d("Retrofit", "something wrong happens...")
                //Toast.makeText(, "Something wrong happens, try again later.", Toast.LENGTH_SHORT).show()
                expressionListLiveData.postValue(null)
            }
        })

        return expressionListLiveData
    }

    //Singleton to instantiate the Repository class
    companion object Factory
    {
        val instance: Repository
            get(){
                return Repository()
            }
    }
}