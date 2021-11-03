package org.sopt.sopthub.ui.view.image

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentImageBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.util.shortToast

class ImageFragment : BindingFragment<FragmentImageBinding>(R.layout.fragment_image) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initAttachBtnClick()
        return binding.root
    }

    private fun initAttachBtnClick() {
        binding.btnAttach.setOnClickListener {
            val galleryPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (galleryPermission == PackageManager.PERMISSION_GRANTED) { //권한 허가
                accessGallery()
            } else {
                //권한 요청
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> accessGallery()
                false -> requireContext().shortToast("갤러리 권한을 허용해주세요.")
            }
        }

    private fun accessGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        requestImage.launch(intent)
    }

    private val requestImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val intent = activityResult.data
                val uri = requireNotNull(intent?.data)
                Glide.with(this).load(uri).into(binding.ivPicture)
            }
        }


}