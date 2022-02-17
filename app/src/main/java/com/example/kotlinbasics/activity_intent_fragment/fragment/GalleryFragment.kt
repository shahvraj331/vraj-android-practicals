package com.example.kotlinbasics.activity_intent_fragment.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.kotlinbasics.R
import com.example.kotlinbasics.activity_intent_fragment.FragmentViewModel
import com.example.kotlinbasics.commonFolder.utils.Constants.HUNDRED
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.ByteArrayOutputStream

class GalleryFragment : Fragment() {

    private var fabOpened = false
    private lateinit var mainFab: ExtendedFloatingActionButton
    private lateinit var galleryFab: FloatingActionButton
    private lateinit var cameraFab: FloatingActionButton
    private lateinit var tvGallery: TextView
    private lateinit var tvCamera: TextView
    private lateinit var ivSelectedImage: ImageView
    private var imageUri: Uri? = null
    private val viewModel: FragmentViewModel by activityViewModels()

    private var galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentIntent = result.data
            imageUri = currentIntent?.data
            ivSelectedImage.setImageURI(imageUri)
            imageUri?.let { viewModel.updateCurrentImage(it) }
            hideExtraFab()
        }
    }

    private var cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val currentIntent = result.data
            val bitmap = currentIntent?.extras?.get(getString(R.string.intent_data_keyword)) as Bitmap
            ivSelectedImage.setImageBitmap(bitmap)
            imageUri = context?.applicationContext?.let { getImageUriFromBitmap(it, bitmap) }
            imageUri?.let { viewModel.updateCurrentImage(it) }
            hideExtraFab()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        mainFab = view.findViewById(R.id.extFabImagePicker)
        galleryFab = view.findViewById(R.id.fabGalleryPicker)
        cameraFab = view.findViewById(R.id.fabCameraPicker)
        tvGallery = view.findViewById(R.id.tvGallery)
        tvCamera = view.findViewById(R.id.tvCamera)
        ivSelectedImage = view.findViewById(R.id.ivSelectedImage)

        hideExtraFab()
        imageUri = viewModel.retrieveCurrentImage()
        if (imageUri != null) {
            ivSelectedImage.setImageURI(imageUri)
        }

        mainFab.setOnClickListener {
            if (!fabOpened) {
                displayExtraFab()
            } else {
                hideExtraFab()
            }
        }

        galleryFab.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            galleryResultLauncher.launch(intent)
        }

        cameraFab.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResultLauncher.launch(intent)
        }

        return view
    }

    private fun displayExtraFab() {
        fabOpened = true
        mainFab.extend()
        galleryFab.show()
        cameraFab.show()
        tvGallery.visibility = View.VISIBLE
        tvCamera.visibility = View.VISIBLE
    }

    private fun hideExtraFab() {
        fabOpened = false
        mainFab.shrink()
        galleryFab.hide()
        cameraFab.hide()
        tvGallery.visibility = View.INVISIBLE
        tvCamera.visibility = View.INVISIBLE
    }

    private fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri?{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, HUNDRED, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, getString(R.string.image_name), null)
        return Uri.parse(path.toString())
    }
}