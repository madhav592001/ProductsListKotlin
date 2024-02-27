package com.example.ecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var productView : RecyclerView
    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productView = findViewById(R.id.productListView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object:Callback<MyData?>{

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("Error","OnFailure "+t.message)
            }

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                var responseBody = response.body()
                var productArray = responseBody?.products!!
                myAdapter = MyAdapter(this@MainActivity,productArray)
                productView.adapter = myAdapter
                productView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

        })
    }
}