package org.sopt.sopthub.ui.view.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.sopthub.R
import org.sopt.sopthub.databinding.FragmentOnBoardMessageBinding
import org.sopt.sopthub.ui.base.BindingFragment

class OnBoardMessageFragment : BindingFragment<FragmentOnBoardMessageBinding>(R.layout.fragment_on_board_message) {

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
            findNavController().navigate(R.id.action_onBoardMessageFragment_to_onBoardFinishFragment)
        }
    }
}