package com.example.android.android_me.ui.GridFragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets
import java.lang.ClassCastException

class MasterListFragment: Fragment(){

    var items: List<Int>

    lateinit var mCallBack: OnImageClickListener
    interface OnImageClickListener{
        fun onImageSelected(position:Int)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try{
            this.mCallBack = context as OnImageClickListener
        }catch (e: ClassCastException){

        }
    }

    init {
        this.items = AndroidImageAssets.getAll()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView:View = inflater!!.inflate(R.layout.fragment_master_list, container, false)
        var gridView:GridView = rootView.findViewById(R.id.gridview) as GridView

        this.items?.let {
            gridView.adapter = MasterListAdapter(this.context, it)
        }

        //Set a clic listener on the gridView and trigger the callback when an item is clicked
        gridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            this.mCallBack.onImageSelected(position)
        })

        return  rootView
    }
}