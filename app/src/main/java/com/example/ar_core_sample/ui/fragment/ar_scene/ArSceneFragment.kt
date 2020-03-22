package com.example.ar_core_sample.ui.fragment.ar_scene

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.ar_core_sample.R
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArSceneFragment : Fragment() {

    private val arSceneViewModel: ArSceneViewModel by viewModel()
    private var yodaModel: ModelRenderable? = null
    lateinit var arFragment: ArFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_ar_scene, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arFragment = childFragmentManager.findFragmentById(R.id.arView) as ArFragment
        loadModel()
    }

    private fun initTapListener() {
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchorNode = AnchorNode(
                hitResult.createAnchor()
            )
            anchorNode.setParent(arFragment.arSceneView.scene)
            val yodaNode = Node()
            yodaNode.renderable = yodaModel
            yodaNode.setParent(anchorNode)
        }
    }

    private fun loadModel() {
        lifecycleScope.launch {
            yodaModel = ModelRenderable
                .builder()
                .setSource(
                    context,
                    Uri.parse("scene.sfb")
                )
                .build()
                .await()
            initTapListener()
        }
    }
}