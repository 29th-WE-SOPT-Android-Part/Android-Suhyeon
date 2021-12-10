package org.sopt.sopthub.ui.view.onboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentOnBoardFinishBinding
import org.sopt.sopthub.ui.base.BindingFragment
import org.sopt.sopthub.ui.view.user.SignInActivity
import org.sopt.sopthub.data.local.SOPTHubSharedPreferences

class OnBoardFinishFragment : BindingFragment<FragmentOnBoardFinishBinding>(R.layout.fragment_on_board_finish) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setBtnStartClick()
        return binding.root
    }

    private fun setBtnStartClick() {
        binding.btnStart.setOnClickListener {
            SOPTHubSharedPreferences.setIsOnBoarding(true)
            startActivity(Intent(requireContext(), SignInActivity::class.java))
            requireActivity().finish()
        }
    }
}