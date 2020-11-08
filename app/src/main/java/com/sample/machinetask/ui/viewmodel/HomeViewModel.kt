package com.sample.machinetask.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sample.machinetask.model.APIResponse
import com.sample.machinetask.network.RestClient
import com.sample.machinetask.repository.DataRepository

class HomeViewModel constructor(mContext: Context): ViewModel()
{

    val doggosPagingFlow = Pager(PagingConfig(pageSize = 10)) {
        DataRepository(RestClient.getInstance().getApiService())
    }.flow.cachedIn(viewModelScope)

    //ViewModel Factory to pass context
    class Factory( val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(context) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }

    }
}