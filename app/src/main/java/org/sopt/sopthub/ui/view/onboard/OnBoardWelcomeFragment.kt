package org.sopt.sopthub.ui.view.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentOnBoardWelcomeBinding
import org.sopt.sopthub.ui.base.BindingFragment

class OnBoardWelcomeFragment : BindingFragment<FragmentOnBoardWelcomeBinding>(R.layout.fragment_on_board_welcome) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setBtnNextClick()
        return binding.root
    }

    private fun setBtnNextClick() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardWelcomeFragment_to_onBoardMessageFragment)
        }
    }
}