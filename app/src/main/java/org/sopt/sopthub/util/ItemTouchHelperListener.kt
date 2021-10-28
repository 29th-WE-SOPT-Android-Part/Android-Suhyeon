package org.sopt.sopthub.util

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemSwipe(position: Int)
}