package net.ukr.zubenko.g.draganddraw

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import net.ukr.zubenko.g.criminalintent.SingleFragmentActivity

class DragAndDrawActivity : SingleFragmentActivity() {
    override fun createFragment() = DragAndDrawFragment.newInstance()

}
