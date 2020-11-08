package com.sample.machinetask.ui.detailsfragment

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sample.machinetask.R
import com.sample.machinetask.databinding.FragmentDetailsBinding
import com.sample.machinetask.model.APIResponse
import com.sample.machinetask.ui.viewmodel.HomeViewModel


class DetailsFragment : Fragment(){


    private lateinit var binding: FragmentDetailsBinding


    lateinit var  model : APIResponse.Article

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        binding.lifecycleOwner = this

        val bundle = this.arguments




        model = bundle!!.getParcelable("details")!!


        binding.model = model

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }
}