package com.sample.machinetask.utility

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sample.machinetask.network.ConnectionLiveData

abstract class BaseActivity<in T>(@LayoutRes private val layoutResId: Int? = null): AppCompatActivity() where T: ViewDataBinding {


    var connectionLiveData: MutableLiveData<Boolean> = MutableLiveData(false)





    abstract fun onActivityCreated(dataBinder: T)

    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this@BaseActivity.initial()
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@BaseActivity.initial()
    }

    private fun initial() {



        connectionLiveData = ConnectionLiveData(this)


        this@BaseActivity.layoutResId?.let { layoutId ->
            val dataBinder = DataBindingUtil.setContentView<T>(this@BaseActivity, layoutId)
            this@BaseActivity.onActivityCreated(dataBinder)
        }


        connectionLiveData.observe(this, Observer {

                it ->

            if (it) {

                Toast.makeText(this,"Internet Connection on",Toast.LENGTH_SHORT).show()


            } else {

                Toast.makeText(this,"Internet Connection off",Toast.LENGTH_SHORT).show()

            }


        })
    }

    fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
        ActivityCompat.checkSelfPermission(this, permission)

    fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

    fun AppCompatActivity.requestPermissionsCompat(
        permissionsArray: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(this, permissionsArray, requestCode)

    }
}