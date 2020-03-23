package com.example.ar_core_sample.ui.fragment.library

import android.graphics.BitmapFactory
import android.os.Bundle
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
    }

    private fun initAdapter() {
        val horizontalManager = GridLayoutManager(context, 2)

        rv_library.setHasFixedSize(true)
        rv_library.layoutManager = horizontalManager
        rv_library.adapter = gridLibraryAdapter

        val tempList = ArrayList<ImageModel>()
        val listImagesFromAssets: Array<String?> = context!!.assets.list("img")!!
        for (image in listImagesFromAssets) {
            val ims = context!!.assets.open("img/$image")
            val bitmap = BitmapFactory.decodeStream(ims)
            tempList.add(ImageModel(bitmap))
        }
        gridLibraryAdapter.setImageData(tempList)
    }
}