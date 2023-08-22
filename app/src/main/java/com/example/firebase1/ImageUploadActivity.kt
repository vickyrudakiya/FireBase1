package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase1.databinding.ActivityUpdatedataBinding

class ImageUploadActivity : AppCompatActivity() {
    lateinit var Binding : ActivityUpdatedataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityUpdatedataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

    }
}