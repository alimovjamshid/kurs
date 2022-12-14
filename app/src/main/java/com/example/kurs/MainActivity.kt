package com.example.kurs

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kurs.adapter.Adapter
import com.example.kurs.databinding.ActivityMainBinding
import com.example.kurs.retrofit.ApiClient
import com.example.kurs.retrofit.models.Data
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: Dialog
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog=Dialog(this)

        val call = ApiClient.apiservice.getdata()
        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.e("onResponse", body.toString())
                    adapter = Adapter( object : Adapter.OnItemDataClick {
                        override fun onItemClick(marvelData: Data) {
                            val calculateDialog = CalculateDialog(marvelData)
                            calculateDialog.show(supportFragmentManager,"hdsakjh")

                        }
                    })
                    adapter.submitList(response.body())
                    binding.rv.adapter = adapter

                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

        })
    }
}