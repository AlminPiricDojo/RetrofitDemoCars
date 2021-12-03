package com.example.retrofitdemocars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var carModels: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carModels = arrayListOf()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(carModels)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        // first we get our API Client
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        // here we use the enqueue callback to make sure that we get the data before we update the Recycler View
        // enqueue gives us async functionality like coroutines, later we will replace this with coroutines
        apiInterface?.getCars()?.enqueue(object: Callback<Cars>{
            override fun onResponse(call: Call<Cars>, response: Response<Cars>) {
                // we use a try block to make sure that our app doesn't crash if the data is incomplete
                try{
                    // now we have access to all cars from the JSON file, we will only use the first car in this demo (index value 0)
                    carModels.add(response.body()!![0].model)
                    rvAdapter.notifyDataSetChanged()
                    // we will also print the full car information here to mimic the previous lesson
                    println("MAKE: ${response.body()!![0].make}")
                    println("MODEL: ${response.body()!![0].model}")
                    println("YEAR: ${response.body()!![0].year}")
                    println("OWNERS: ${response.body()!![0].owners}")
                }catch(e: Exception){
                    Log.d("MAIN", "ISSUE: $e")
                }
            }

            override fun onFailure(call: Call<Cars>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }

        })
    }
}