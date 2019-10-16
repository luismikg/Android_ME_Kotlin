package com.example.android.android_me.ui.GridFragment

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets
import com.example.android.android_me.ui.BodyPart.AndroidMeActivity
import com.example.android.android_me.ui.BodyPart.BodyPartFragment
import kotlinx.android.synthetic.main.fragment_master_list.*


class MainActivity: AppCompatActivity(), MasterListFragment.OnImageClickListener {

    private var mTwoPane: Boolean = false

    private var headIndex:Int = 0
    private var bodyIndex:Int = 0
    private var legIndex:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<LinearLayout>(R.id.android_me_linear_layout)?.let {
            this@MainActivity.mTwoPane = true
            this@MainActivity.initMeComponents( savedInstanceState )
        }

        if (this@MainActivity.mTwoPane) {
            this@MainActivity.nextButton.visibility = View.GONE
            val gridView:GridView = findViewById(R.id.gridview)
            gridView.numColumns = 2
        } else{
            this@MainActivity.nextButton.visibility = View.VISIBLE
        }
    }

    private fun initUI(intent: Intent){
        nextButton.setOnClickListener(View.OnClickListener {
            this@MainActivity.startActivity(intent)
        })
    }

    override fun onImageSelected(position: Int) {

        Toast.makeText(this@MainActivity, "clicked: $position", Toast.LENGTH_SHORT).show()

        var bodyPartNumber = position/12
        var listIndex = position - 12*bodyPartNumber

        when(bodyPartNumber){
            0 -> this@MainActivity.headIndex = listIndex
            1 -> this@MainActivity.bodyIndex = listIndex
            2 -> this@MainActivity.legIndex = listIndex
        }

        if (this@MainActivity.mTwoPane) {
            this@MainActivity.onImageSelectedForBigDisplay( bodyPartNumber, listIndex )
        } else{
            this@MainActivity.onImageSelectedForSmallDisplay( bodyPartNumber, listIndex )
        }
    }

    private fun onImageSelectedForSmallDisplay(bodyPartNumber:Int, listIndex:Int){

        val b: Bundle = Bundle()
        b.putInt("headIndex", this@MainActivity.headIndex)
        b.putInt("bodyIndex", this@MainActivity.bodyIndex)
        b.putInt("legIndex", this@MainActivity.legIndex)

        val intent = Intent(this@MainActivity, AndroidMeActivity::class.java)
        intent.putExtras(b)

        this@MainActivity.initUI(intent)
    }

    //Only when this app is running in a tablet
    private fun initMeComponents( savedInstanceState: Bundle? ) {
        if ( savedInstanceState!=null ){
            return
        }

        //HEAD
        val headFragment = BodyPartFragment()
        headFragment.mImageIds = AndroidImageAssets.getHeads()
        headFragment.mListIndex = 1

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit()

        //BODY
        val bodyFragment = BodyPartFragment()
        bodyFragment.mImageIds = AndroidImageAssets.getBodies()
        bodyFragment.mListIndex = 1
        fragmentManager.beginTransaction()
                .add(R.id.body_container, bodyFragment)
                .commit()

        //LEGS
        val legsFragment = BodyPartFragment()
        legsFragment.mImageIds = AndroidImageAssets.getLegs()
        legsFragment.mListIndex = 3
        fragmentManager.beginTransaction().add(R.id.leg_container, legsFragment).commit()
    }

    private fun onImageSelectedForBigDisplay(bodyPartNumber:Int, listIndex:Int){

        val fragmentManager = supportFragmentManager

        when(bodyPartNumber){
            0 -> {
                //HEAD
                val headFragment = BodyPartFragment()
                headFragment.mImageIds = AndroidImageAssets.getHeads()
                headFragment.mListIndex = this@MainActivity.headIndex

                fragmentManager.beginTransaction()
                        .replace(R.id.head_container, headFragment)
                        .commit()
            }

            1 -> {
                //BODY
                val bodyFragment = BodyPartFragment()
                bodyFragment.mImageIds = AndroidImageAssets.getBodies()
                bodyFragment.mListIndex = this@MainActivity.bodyIndex
                fragmentManager.beginTransaction()
                        .replace(R.id.body_container, bodyFragment)
                        .commit()
            }

            2 -> {
                //LEGS
                val legsFragment = BodyPartFragment()
                legsFragment.mImageIds = AndroidImageAssets.getLegs()
                legsFragment.mListIndex = this@MainActivity.legIndex
                fragmentManager.beginTransaction().replace(R.id.leg_container, legsFragment).commit()

            }
        }
    }
}

