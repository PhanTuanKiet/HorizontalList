package com.example.tuankiet.horizontallist

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.*

class RecyclerViewAdapter(val itemList : ArrayList<ItemModel>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemListViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemListViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_layout,p0,false)
        return ItemListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItemByPosition(position: Int): ItemModel? {
        return itemList[position]
    }


    override fun onBindViewHolder(p0: ItemListViewHolder, p1: Int) {
        return p0.bindItems(itemList[p1],randomColor())
    }

    fun randomColor(): Int {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        return color
    }


    class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var isSetcolor = false
        fun bindItems(item: ItemModel?,itemColor : Int) {
            if (item != null) {
                if (!isSetcolor){
                    isSetcolor = true
                    itemView.cardLayout.setCardBackgroundColor(itemColor)
                }
                var string = breakLine(item.keyword)
                itemView.keywordTextView.text = string
            }
        }

        fun breakLine(string : String): String {
            val listString = string.trim().split(" ")
            val count = listString.count()
            var stringResult = ""
            for (i in 0..listString.size-1){
                if (count > 1 && i == count/2)
                    stringResult += "\n"
                stringResult += " " + listString[i]
            }
            return stringResult
        }
    }
}