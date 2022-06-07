package br.com.poccompose.ui

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.poccompose.R
import br.com.poccompose.ui.components.AppCamera
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DemoCameraXActivity : AppCompatActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> =  mutableStateOf(false)


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if(isGranted){
            Log.d("CAMERA_APP","Permission OK")
            shouldShowCamera.value = true
        }else{
            Log.d("CAMERA_APP","Permission DENIED")
        }
    }

    private fun requestCameraPermission(){
        val checkSelfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val cameraDialog = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
        when{
            checkSelfPermission == PackageManager.PERMISSION_GRANTED ->{
                //Permission previously granted
                shouldShowCamera.value = true
            }
            cameraDialog ->{
                //Show camera permission dialog
            }
            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if(shouldShowCamera.value){
                AppCamera(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCapture = ::handleImageCapture,
                    onError = {}
                )
            }
        }
    }

    private fun handleImageCapture(uri: Uri){
        shouldShowCamera.value = false
    }

    private fun getOutputDirectory(): File{
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if(mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}