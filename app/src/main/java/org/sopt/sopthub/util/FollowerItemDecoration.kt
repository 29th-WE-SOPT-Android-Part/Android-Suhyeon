package org.sopt.sopthub.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView

class FollowerItemDecoration(
    private val dividerHeight: Int,
    private val padding: Int,
    private val color: Int = Color.TRANSPARENT
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    //item 사이의 간격
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.apply {
            top = padding
            bottom = padding
            left = padding
            right = padding
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        paint.color = color

        parent.forEach { view ->

            val dividerTop = view.bottom + padding //dividerTop 위치를 padding 만큼 내림
            val dividerBottom = dividerTop + dividerHeight

            c.drawRect(
                view.left.toFloat(),
                dividerTop.toFloat(),
                view.right.toFloat(),
                dividerBottom.toFloat(),
                paint
            )
        }
    }
}