package org.sopt.sopthub.ui.view.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentImageBinding
import org.sopt.sopthub.ui.base.BindingFragment

class ImageFragment : BindingFragment<FragmentImageBinding>(R.layout.fragment_image) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}