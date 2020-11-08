package com.sample.machinetask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sample.machinetask.databinding.ActivityMainBinding
import com.sample.machinetask.ui.HomeActivity
import com.sample.machinetask.ui.viewmodel.HomeViewModel
import com.sample.machinetask.utility.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    lateinit var anim : Animation

    lateinit var binding : ActivityMainBinding

    var localCheck  = false


    companion object{
        const val API_KEY = "72c2cf7d168d421e8e60b90b4d266273"
    }

    override fun onActivityCreated(dataBinder: ActivityMainBinding) {
        binding = dataBinder
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in); // Create the animation.


        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {

                localCheck = true

                if(connectionLiveData.value!!) {
                    updateDisplay()
                }


            }

            override fun onAnimationRepeat(animation: Animation) {}


        })
        binding.image.startAnimation(anim)


        connectionLiveData.observe(this, Observer {
                it ->
            if(it) {
                if(localCheck) {
                    updateDisplay()
                }
            }
        })

    }


    fun updateDisplay()
    {
        val i  = Intent(this@MainActivity,HomeActivity::class.java)
        startActivity(i)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        finish()
    }
}