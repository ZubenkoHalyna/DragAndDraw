package net.ukr.zubenko.g.draganddraw

import android.graphics.PointF
import android.os.Parcelable
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.Serializable
import java.io.ObjectInputStream


data class Box(var mCurrent: PointF,
               var mOrigin: PointF = mCurrent,
               var rotationPoint: PointF = EMPTY_POINT,
               var angle: Float = 0.0f): Serializable {
    companion object {
        val EMPTY_POINT = PointF(0.0f, 0.0f)
        val EMPTY = Box(EMPTY_POINT)
    }

    val dx: Float
        get() = mCurrent.x - mOrigin.x
    val dy: Float
        get() = mCurrent.y - mOrigin.y
    val left: Float
        get() = Math.min(mOrigin.x, mCurrent.x)
    val right: Float
        get() = Math.max(mOrigin.x, mCurrent.x)
    val top: Float
        get() = Math.min(mOrigin.y, mCurrent.y)
    val bottom: Float
        get() = Math.max(mOrigin.y, mCurrent.y)

    private fun writeObject(aOutputStream: ObjectOutputStream) {
        aOutputStream.writeFloat(mCurrent.y)
        aOutputStream.writeFloat(mCurrent.x)
        aOutputStream.writeFloat(mOrigin.y)
        aOutputStream.writeFloat(mOrigin.x)
        aOutputStream.writeFloat(rotationPoint.y)
        aOutputStream.writeFloat(rotationPoint.x)
        aOutputStream.writeFloat(angle)
    }

    private fun readObject(aInputStream: ObjectInputStream) {
        angle = aInputStream.readFloat()
        rotationPoint = PointF(aInputStream.readFloat(), aInputStream.readFloat())
        mOrigin = PointF(aInputStream.readFloat(), aInputStream.readFloat())
        mCurrent = PointF(aInputStream.readFloat(), aInputStream.readFloat())
    }
}