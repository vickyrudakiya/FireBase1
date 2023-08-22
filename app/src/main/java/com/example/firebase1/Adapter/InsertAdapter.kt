package com.example.firebase1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase1.ModelClass.InsertModelClass
import com.example.firebase1.R

class InsertAdapter(
    var list: ArrayList<InsertModelClass>,
    var onitemclick: (String, String, String, String, String) -> Unit,
    var onitemdelet: ((String) -> Unit)
) : RecyclerView.Adapter<InsertAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.txtname)
        var Addresh: TextView = itemView.findViewById(R.id.txtAddresh)
        var Product: TextView = itemView.findViewById(R.id.txtProduct)
        var Number: TextView = itemView.findViewById(R.id.txtnumber)
        var img: ImageView = itemView.findViewById(R.id.ImgEdit)
        var delet: ImageView = itemView.findViewById(R.id.Imgdelet)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsertAdapter.MyViewHolder {

        var View =
            LayoutInflater.from(parent.context).inflate(R.layout.insert_item_view, parent, false)
        return MyViewHolder(View)
    }

    override fun onBindViewHolder(holder: InsertAdapter.MyViewHolder, position: Int) {

        holder.name.text = list[position].name
        holder.Addresh.text = list[position].addresh
        holder.Product.text = list[position].product
        holder.Number.text = list[position].number
        holder.img.setOnClickListener {
            onitemclick.invoke(
                list[position].name,
                list[position].addresh,
                list[position].product,
                list[position].number,
                list[position].key
            )
        }
//        holder.delet.setOnClickListener {
//            onitemdelet.invoke(list[position].key)
//        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}
