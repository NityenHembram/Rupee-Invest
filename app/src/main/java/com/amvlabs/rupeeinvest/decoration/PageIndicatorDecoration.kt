package com.amvlabs.rupeeinvest.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max


class PageIndicatorDecoration : RecyclerView.ItemDecoration() {

    private val  mIndicatorItemLength = 35f
    var mIndicatorItemPadding  = 16f
    var mIndicatorHeight  = 100f

    val mPaint = Paint()


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount: Int = parent.adapter!!.itemCount


        // center horizontally, calculate width and subtract half from center

        // center horizontally, calculate width and subtract half from center
        val totalLength: Float = mIndicatorItemLength * itemCount
        val paddingBetweenItems: Float = max(0, itemCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX: Float = (parent.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space

        // center vertically in the allotted space
        val indicatorPosY: Float = parent.height - mIndicatorHeight / 2f

        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount)

        // find active page (which should be highlighted)
        // find active page (which should be highlighted)
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val activePosition = layoutManager.findFirstVisibleItemPosition()
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

// find offset of active page (if the user is scrolling)

// find offset of active page (if the user is scrolling)
        val activeChild = layoutManager.findViewByPosition(activePosition)
        val left = activeChild!!.left
        val width = activeChild!!.width

// on swipe the active item will be positioned from [-width, 0]
// interpolate offset for smooth animation

// on swipe the active item will be positioned from [-width, 0]
// interpolate offset for smooth animation
        var mInterpolator = AccelerateDecelerateInterpolator()
        val progress: Float = mInterpolator.getInterpolation(left * -1 / width.toFloat())

        // draw highlighted line
        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
    }

    private fun drawHighlights(
        c: Canvas, indicatorStartX: Float, indicatorPosY: Float,
        highlightPosition: Int, progress: Float, itemCount: Int
    ) {
        mPaint.color = Color.YELLOW

        // width of item indicator including padding
        val itemWidth = mIndicatorItemLength + mIndicatorItemPadding
        if (progress == 0f) {
            // no swipe, draw a normal indicator
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            c.drawLine(
                highlightStart, indicatorPosY,
                highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
            )
        } else {
            var highlightStart = indicatorStartX + itemWidth * highlightPosition
            // calculate partial highlight
            val partialLength = mIndicatorItemLength * progress

            // draw the cut off highlight
            c.drawLine(
                highlightStart + partialLength, indicatorPosY,
                highlightStart + mIndicatorItemLength, indicatorPosY, mPaint
            )

            // draw the highlight overlapping to the next item as well
            if (highlightPosition < itemCount - 1) {
                highlightStart += itemWidth
                c.drawLine(
                    highlightStart, indicatorPosY,
                    highlightStart + partialLength, indicatorPosY, mPaint
                )
            }
        }
    }


    private fun drawInactiveIndicators(
        c: Canvas, indicatorStartX: Float,
        indicatorPosY: Float, itemCount: Int
    ) {

        mPaint.color = Color.WHITE

        // width of item indicator including padding
        val itemWidth: Float = mIndicatorItemLength + mIndicatorItemPadding
        var start = indicatorStartX
        for (i in 0 until itemCount) {
            // draw the line for every item
            c.drawLine(
                start, indicatorPosY,
                start + mIndicatorItemLength, indicatorPosY, mPaint
            )
            start += itemWidth
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = 10;
    }
}