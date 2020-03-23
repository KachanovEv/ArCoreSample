package com.example.ar_core_sample.ui.fragment.library

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ar_core_sample.R
import com.example.ar_core_sample.data.model.ImageModel
import com.example.ar_core_sample.ui.adapter.LibraryAdapter
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : Fragment() {

    private val gridLibraryAdapter = LibraryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        btnAddImage.setOnClickListener {
            pickFromGallery()
        }
    }

    private fun pickFromGallery() {
        val PICK_PHOTO_REQUEST: Int = 1
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        startActivityForResult(pickImageIntent, PICK_PHOTO_REQUEST)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        data: Intent?
    ) {

        val PICK_PHOTO_REQUEST: Int = 1

        if (resultCode == Activity.RESULT_OK
            && requestCode == PICK_PHOTO_REQUEST
        ) {
            //photo from gallery
            val fileUri: Uri? = data?.data
            val source = ImageDecoder.createSource(context?.contentResolver!!, fileUri!!)
            val bitmap = ImageDecoder.decodeBitmap(source)
            val imageList = getListImagesFromAssets()
            imageList.add(
                getListImagesFromAssets().size - 1,
                ImageModel(bitmap)
            )
            gridLibraryAdapter.setImageData(imageList)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initAdapter() {
        val horizontalManager = GridLayoutManager(context, 2)

        rv_library.setHasFixedSize(true)
        rv_library.layoutManager = horizontalManager
        rv_library.adapter = gridLibraryAdapter

        gridLibraryAdapter.setImageData(getListImagesFromAssets())
    }

    private fun getListImagesFromAssets(): ArrayList<ImageModel> {
        val tempList = ArrayList<ImageModel>()
        val listImagesFromAssets: Array<String?> = context!!.assets.list("img")!!
        for (image in listImagesFromAssets) {
            val ims = context!!.assets.open("img/$image")
            val bitmap = BitmapFactory.decodeStream(ims)
            tempList.add(ImageModel(bitmap))
        }
        return tempList
    }
}