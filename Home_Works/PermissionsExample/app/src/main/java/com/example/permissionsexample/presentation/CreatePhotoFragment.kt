package com.example.permissionsexample.presentation

import android.app.Application
import android.content.ContentResolver
import android.content.ContentValues
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.permissionsexample.App
import com.example.permissionsexample.databinding.FragmentCreatePhotoBinding
import com.example.permissionsexample.model.PathToImage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss"

class CreatePhotoFragment : Fragment() {
    private var _binding: FragmentCreatePhotoBinding? = null
    val binding get() = _binding!!
    private lateinit var executor: Executor
    private var imageCapture:ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePhotoBinding.inflate(inflater)

        executor = ContextCompat.getMainExecutor(this.requireContext())

        binding.createPhotoButton.setOnClickListener {
            createPhoto()
        }

        startCamera()

        return binding.root
    }

    private fun createPhoto() {
        val imageCapture = imageCapture ?: return

        var image = PathToImage(path = "", name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()))

        val consValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, image.name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }

        val outputOptions =
            ImageCapture.OutputFileOptions.Builder(
                requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                consValues
            ).build()
        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    image.path = outputFileResults.savedUri.toString()
                    Toast.makeText(requireContext(),"Фото сохранено",Toast.LENGTH_LONG).show()
                    lifecycleScope.launch {
                        App.db.PathToImageDAO().add(image)
                    }
                    findNavController().popBackStack()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(requireContext(),exception.message,Toast.LENGTH_LONG).show()
                }
            }
        )
    }




    private fun startCamera() {
        if (App.permissionsGranted){
            val cameraProvider = ProcessCameraProvider.getInstance(this.requireContext())
            cameraProvider.addListener({

                val cameraProvider = cameraProvider.get()
                val preview = Preview.Builder().build()
                preview.surfaceProvider = binding.photo.surfaceProvider
                imageCapture = ImageCapture.Builder().build()

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture
                )

            }, executor)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}