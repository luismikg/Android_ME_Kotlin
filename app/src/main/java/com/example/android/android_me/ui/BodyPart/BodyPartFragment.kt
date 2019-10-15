package com.example.android.android_me.ui.BodyPart

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.android_me.R
import java.util.ArrayList

class BodyPartFragment: Fragment() {

    //TAGS:
    companion object{ //static
        //const val = final
        private const val TAG: String = "BodyPartFragment"
        private const val IMAGE_ID_LIST = "image_ids"
        private const val LIST_INDEX = "list_index"
    }


    var mImageIds: List<Int>?
    var mListIndex: Int

    init {
        this.mImageIds = null
        this.mListIndex = 0
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if ( savedInstanceState != null){
            this.mListIndex = savedInstanceState.getInt(LIST_INDEX)
            this.mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST)
        }

        var rootView:View = inflater!!.inflate(R.layout.fragment_body_part, container, false)
        val imageView:ImageView = rootView.findViewById(R.id.body_part_image_view) as ImageView

        if ( this.mImageIds.isNullOrEmpty() ) {
            Log.v(TAG, "Este fragmento tiene nulo a su lista de id's")
        }
        else {

            imageView.setImageResource( this.mImageIds!![this.mListIndex] )

            imageView.setOnClickListener( View.OnClickListener {
                if ( this.mListIndex<this.mImageIds!!.size-1 ) {
                    this.mListIndex++
                }else {
                    this.mListIndex = 0
                }
                imageView.setImageResource( this.mImageIds!![this.mListIndex] )
            })
        }

        return imageView
    }

    override fun onSaveInstanceState(outState: Bundle?) {

        outState!!.putIntegerArrayList(IMAGE_ID_LIST, this.mImageIds as ArrayList<Int>? )
        outState!!.putInt(LIST_INDEX, this.mListIndex )
    }
}