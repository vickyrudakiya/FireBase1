package com.example.firebase1

import android.location.Address
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebase1.ModelClass.InsertModelClass
import com.example.firebase1.databinding.ActivityUpdatedataBinding
import com.google.firebase.database.FirebaseDatabase

class UpdatedataActivity : AppCompatActivity() {
    lateinit var Binding : ActivityUpdatedataBinding
    var key : String=" "
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityUpdatedataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        var reference = FirebaseDatabase.getInstance().getReference("ProductTb")

        if(intent!=null){
           var name = intent.getStringExtra("name").toString()
           var Address = intent.getStringExtra("addresh").toString()
           var Product = intent.getStringExtra("product").toString()
           var number = intent.getStringExtra("number").toString()

            key = intent.getStringExtra("key").toString()


            Binding.txtName.setText(name)
            Binding.txtaddresh.setText(Address)
            Binding.txtproduct.setText(Product)
            Binding.txtNumber.setText(number)
        }
        Binding.btnadd.setOnClickListener {

            var num1=Binding.txtName.text.toString()
            var num2=Binding.txtaddresh.text.toString()
            var num3=Binding.txtproduct.text.toString()
            var num4=Binding.txtNumber.text.toString()

            var modelClass = InsertModelClass(num1,num2,num3,num4,key)

            reference.root.child("ProductTb").child(key).setValue(modelClass).addOnCompleteListener {

                if (it.isSuccessful){

                    Toast.makeText(this, "data added", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }

            }.addOnFailureListener {
                Log.e("TAG", "initview: "+it )
            }
        }

    }
}