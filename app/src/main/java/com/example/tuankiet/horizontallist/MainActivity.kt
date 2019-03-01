package com.example.tuankiet.horizontallist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }

    fun getData(){
        Thread {
            val result = URL(Constants.URL).readText()
            val itemList = ArrayList<ItemModel>()

            if(result != null) {
                val root = JSONArray(result)
                for (i in 0..root.length() - 1) {
                    val keyword = root.get(i).toString()
                    itemList.add(ItemModel(keyword))
                }
            }

            runOnUiThread {
                val list_adapter = RecyclerViewAdapter(itemList)
                itemRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                itemRecyclerView.adapter = list_adapter
            }
        }.start()
    }
}
