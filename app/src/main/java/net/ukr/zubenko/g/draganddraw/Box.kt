package net.ukr.zubenko.g.draganddraw

import android.graphics.PointF
import android.os.Parcelable
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.Serializable
import java.io.ObjectInputStream


data class Box(var mCurrent: PointF, var mOrigin: PointF = mCurrent): Serializable {
    companion object {
        val EMPTY = Box(PointF(0.0f,0.0f))
    }

    private fun writeObject(aOutputStream: ObjectOutputStream) {
        aOutputStream.writeFloat(mCurrent.y)
        aOutputStream.writeFloat(mCurrent.x)
        aOutputStream.writeFloat(mOrigin.y)
        aOutputStream.writeFloat(mOrigin.x)
    }

    private fun readObject(aInputStream: ObjectInputStream) {
        mOrigin = PointF(aInputStream.readFloat(), aInputStream.readFloat())
        mCurrent = PointF(aInputStream.readFloat(), aInputStream.readFloat())
    }
}