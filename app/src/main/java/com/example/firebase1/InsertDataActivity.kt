package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase1.Adapter.InsertAdapter
import com.example.firebase1.ModelClass.InsertModelClass
import com.example.firebase1.databinding.ActivityInsertDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InsertDataActivity : AppCompatActivity() {
    lateinit var Binding : ActivityInsertDataBinding
    lateinit var MyAdapter : InsertAdapter
    
     var list : ArrayList<InsertModelClass> = ArrayList()
    var reference = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityInsertDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.Button.setOnClickListener {

            var name = Binding.name.text.toString()
            var addresh = Binding.Addresh.text.toString()
            var product = Binding.Product.text.toString()
            var number = Binding.number.text.toString()

            var key = reference.root.child("ProductTb").push().key ?: ""

            var modelClass = InsertModelClass(name,addresh,product,number,key)


            reference.root.child("ProductTb").child(key).setValue(modelClass).addOnCompleteListener {

                if(it.isSuccessful){

                    Toast.makeText(this, "Data added Successfull", Toast.LENGTH_SHORT).show()
                }
            }.addOnCanceledListener { Log.e("TAG", "Error: "+it ) }
        }

        Binding.Show.setOnClickListener {
            reference.root.child("ProductTb").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (child in snapshot.children){
                        var data : InsertModelClass? = child.getValue(InsertModelClass::class.java)
                        list.add(data as InsertModelClass)



//                        var Adapter = InsertAdapter(this@InsertDataActivity,list)
//                        var manager = LinearLayoutManager(this@InsertDataActivity,LinearLayoutManager.VERTICAL,false)
//                        Binding.rcv.adapter= Adapter
//                        Binding.rcv.layoutManager=manager
                    }

                    MyAdapter = InsertAdapter(list, onitemclick = {name,addresh,product,number ,key->


                        Log.e("TAG", "onDataChange: "+name)

                        var i=Intent(this@InsertDataActivity,UpdatedataActivity::class.java)
                        i.putExtra("name",name)
                        i.putExtra("addresh",addresh)
                        i.putExtra("product",product)
                        i.putExtra("number",number)
                        i.putExtra("key",key)

                        startActivity(i)

                    }
                    ,
                        onitemdelet = {key->

                        reference=FirebaseDatabase.getInstance().getReference().child("ProductTb").child(key)
                        reference.removeValue()

                    })
                    var manager = LinearLayoutManager(
                        this@InsertDataActivity,LinearLayoutManager.VERTICAL,false)
                    Binding.rcv.adapter = MyAdapter
                    Binding.rcv.layoutManager = manager
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }


    }
}