package net.ukr.zubenko.g.draganddraw

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent
import android.graphics.PointF
import android.util.Log
import android.graphics.Canvas
import android.os.Parcelable
import android.os.Bundle
import android.util.SparseArray
import java.util.*


class BoxDrawingView(context: Context,
                     private val attrs: AttributeSet? = null):
    View(context, attrs) {

    private var mCurrentBox = Box.EMPTY
    private val mBoxen = mutableListOf<Box>()
    private val mBoxPaint: Paint = DEFAULT_COLOR
    private val mBackgroundPaint: Paint = BACKGROUND_COLOR

    companion object {
        private val TAG = "BoxDrawingView"
        private val DEFAULT_COLOR = Paint()
        private val BACKGROUND_COLOR = Paint()

        init {
            DEFAULT_COLOR.color = 0x22ff0000
            BACKGROUND_COLOR.color = 0xfff8efe0.toInt()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPaint(mBackgroundPaint)  // Заполнение фона
        for (box in mBoxen) {
            val left = Math.min(box.mOrigin.x, box.mCurrent.x)
            val right = Math.max(box.mOrigin.x, box.mCurrent.x)
            val top = Math.min(box.mOrigin.y, box.mCurrent.y)
            val bottom = Math.max(box.mOrigin.y, box.mCurrent.y)
            canvas.drawRect(left, top, right, bottom, mBoxPaint)
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        container?.put(container.size(), onSaveInstanceState())
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        for (i in 0 until container.size()) {
            onRestoreInstanceState(container[i])
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putSerializable("mBoxen", mBoxen.toTypedArray())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        var currentState: Parcelable? = state
        if (state is Bundle) {
            val array = state.getSerializable("mBoxen") as? Array<Box>
            array?.let {
                mBoxen.addAll(array)
            }
            currentState = state.getParcelable("superState")
        }
        super.onRestoreInstanceState(currentState)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current = PointF(event.x, event.y)
        var action = ""

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"
                mCurrentBox = Box(current)
                mBoxen.add(mCurrentBox)
            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"
                if (mCurrentBox != Box.EMPTY) {
                    mCurrentBox.mCurrent = current
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"
                mCurrentBox = Box.EMPTY
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
                mCurrentBox = Box.EMPTY
            }
        }
        Log.i(TAG, action + " at x=" + current.x + ", y=" + current.y)
        return true
    }
}
