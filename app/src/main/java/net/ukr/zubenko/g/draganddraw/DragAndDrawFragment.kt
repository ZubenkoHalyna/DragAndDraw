package net.ukr.zubenko.g.draganddraw

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


class DragAndDrawFragment: Fragment() {
    companion object {
        fun newInstance() = DragAndDrawFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_drag_and_draw, container, false)
}