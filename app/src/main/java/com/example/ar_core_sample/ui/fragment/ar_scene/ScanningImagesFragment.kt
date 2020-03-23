package com.example.ar_core_sample.ui.fragment.ar_scene

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ar_core_sample.R
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.fragment_scanning_images.*
import kotlinx.android.synthetic.main.item_title_augmented_image.view.*
import java.util.HashMap

class ScanningImagesFragment : Fragment() {

    private lateinit var arFragment: ArFragment

    // Augmented image and its center pose anchor
    private val augmentedImageMap = HashMap<AugmentedImage, AnchorNode>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_scanning_images, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arFragment =
            childFragmentManager.findFragmentById(R.id.augment_images) as ArFragment
        arFragment.arSceneView.scene.addOnUpdateListener { updateFrame() }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (augmentedImageMap.isEmpty()) {
            hint_prompt.visibility = View.VISIBLE
        }
    }

    private fun updateFrame() {
        val frame = arFragment.arSceneView.arFrame

        if (frame == null || frame.camera.trackingState != TrackingState.TRACKING) {
            return
        }

        val updatedAugmentedImages = frame.getUpdatedTrackables(AugmentedImage::class.java)

        for (augmentedImage in updatedAugmentedImages) {
            when (augmentedImage.trackingState.name) {

                TrackingState.PAUSED.name -> {
                    Log.d("Paused", augmentedImage.name + " " + augmentedImage.index)
                }

                TrackingState.TRACKING.name -> {

                    hint_prompt.visibility = View.GONE
                    if (!augmentedImageMap.containsKey(augmentedImage)) {
                        val node =
                            AnchorNode(augmentedImage.createAnchor(augmentedImage.centerPose))
                        augmentedImageMap[augmentedImage] = node
                        arFragment.arSceneView.scene.addChild(node)
                        addTextLabel(augmentedImage, node)
                        Log.d("Tracking", augmentedImage.name + " " + augmentedImage.index)
                    }
                }

                TrackingState.STOPPED.name -> {
                    Log.d("Stopped", augmentedImage.name + " " + augmentedImage.index)
                    augmentedImageMap.remove(augmentedImage)
                }
            }
        }
    }

    private fun addTextLabel(augmentedImage: AugmentedImage, anchorNode: AnchorNode) {
        ViewRenderable.builder().setView(context, R.layout.item_title_augmented_image).build()
            .thenAccept { viewRenderable ->
                val noteText = Node()
                noteText.renderable = viewRenderable
                //scale the text
                val localScale = Vector3()
                localScale.set(0.15f, 0.15f, 0.15f)
                noteText.localScale = localScale
                noteText.setParent(anchorNode)
                //change the type of car based on index in database
                Log.d("Tracking", augmentedImage.name + " " + augmentedImage.index)

                viewRenderable.view.tvImageTitle.text = augmentedImage.name.toString()
            }
    }
}