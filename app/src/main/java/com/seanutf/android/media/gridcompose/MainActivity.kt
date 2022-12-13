package com.seanutf.android.media.gridcompose


import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.Companion.isPhotoPickerAvailable
import androidx.appcompat.app.AppCompatActivity
import com.seanutf.android.media.gridcompose.databinding.ActivityLauncherBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    private var pickMultipleMedia: ActivityResultLauncher<PickVisualMediaRequest>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvOpenAndroid.setOnClickListener {
            launch()
        }

        binding.tvOpenCustom.setOnClickListener {
        }

        register()
    }

    private fun register() {
        if (isPhotoPickerAvailable()) {
            pickMultipleMedia =
                registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
                    // Callback is invoked after the user selects media items or closes the
                    // photo picker.
                    if (uris.isNotEmpty()) {
                        Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
                    } else {
                        Log.d("PhotoPicker", "No media selected")
                    }
                }
        }
    }

    private fun launch() {
        pickMultipleMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }
}