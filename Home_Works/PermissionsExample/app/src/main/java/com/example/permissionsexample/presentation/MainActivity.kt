package com.example.permissionsexample.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.Manifest
import android.os.Build
import android.widget.Toast
import com.example.permissionsexample.App
import com.example.permissionsexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map ->
        if (map.values.all { !it }){
            Toast.makeText(this,"Permission isnot granted",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        checkPermissions()
    }

    private fun checkPermissions(){
        App.permissionsGranted = REQUEST_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        if (!App.permissionsGranted){
            launcher.launch(REQUEST_PERMISSIONS)
        }
    }

    companion object{
        private val REQUEST_PERMISSIONS: Array<String> = buildList {
            add(Manifest.permission.CAMERA)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}