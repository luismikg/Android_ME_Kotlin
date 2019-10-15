/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui.BodyPart

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets

// This activity will display a custom Android image composed of three body parts: head, body, and legs
class AndroidMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)

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
}
