package com.example.pythoncompiler

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.pythoncompiler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if(!Python.isStarted())
            Python.start(AndroidPlatform(this))

        binding.runBtn.setOnClickListener{
            val py = Python.getInstance()
            val pyObj = py.getModule("myScript")

            val result = pyObj.callAttr("main", binding.pythonCode.text.toString()).toString()

            if(result.length >= 200){
                binding.codeOutput.visibility = View.GONE
                binding.plotImg.visibility = View.VISIBLE
                val plotImgData = Base64.decode(result, Base64.DEFAULT)
                val plotImgBitmap = BitmapFactory.decodeByteArray(plotImgData, 0, plotImgData.size)
                binding.plotImg.setImageBitmap(plotImgBitmap)
            }
            else {
                binding.plotImg.visibility = View.GONE
                binding.codeOutput.visibility = View.VISIBLE
                binding.codeOutput.text = result
            }
        }
    }
}