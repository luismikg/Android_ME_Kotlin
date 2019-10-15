package com.example.android.android_me.ui.GridFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets

class MasterListFragment: Fragment(){

    var items: List<Int>

    init {
        this.items = AndroidImageAssets.getAll()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView:View = inflater!!.inflate(R.layout.fragment_master_list, container, false)
        var gridView:GridView = rootView.findViewById(R.id.gridview) as GridView

        this.items?.let {
            gridView.adapter = MasterListAdapter(this.context, it)
        }

        return  rootView
    }
}