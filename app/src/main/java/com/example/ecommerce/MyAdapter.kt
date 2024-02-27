package com.example.ecommerce

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context:Activity,val productList:List<Product>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Layout manager fails to create view for data then this method is called
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false)
        return MyViewHolder(itemView)
    }

    // after creating view, populate the data in the view
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.title.text = currentItem.title
        Picasso.get().load(currentItem.thumbnail).into(holder.img);
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var img:ShapeableImageView
        var title: TextView

        init {
            img = itemView.findViewById(R.id.productImg)
            title = itemView.findViewById(R.id.productName)
        }
    }

}