package com.example.android.android_me.ui.GridFragment

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MasterListAdapter( context: Context, allImages:List<Int>): BaseAdapter() {

    var  allImages: List<Int>
    var context:Context

    init {
        this.allImages = allImages
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView

        convertView?.let {
            imageView = it as ImageView
            imageView.setImageResource( this.allImages[position] )
            return imageView
        }

        imageView = ImageView( this.context )
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setPadding(8,8,8,8)
        imageView.setImageResource( this.allImages[position] )
        return imageView
    }

    override fun getCount(): Int {
        var leght:Int? =  this.allImages?.let {
            it.count()
        }

        if( leght == null ) leght = 0
        return leght

        //return this.allImages!!.count()
    }

    override fun getItem(position: Int): Any {
        return this.allImages[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}