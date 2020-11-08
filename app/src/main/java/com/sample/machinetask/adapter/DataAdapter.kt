package com.sample.machinetask.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.machinetask.R
import com.sample.machinetask.databinding.ListArticlesBinding
import com.sample.machinetask.databinding.ListTextBinding
import com.sample.machinetask.model.APIResponse
import com.sample.machinetask.ui.homefragment.HomeFragment

class DataAdapter(context: Context) : PagingDataAdapter<APIResponse.Article, RecyclerView.ViewHolder>(ARTICLES_COMPARATOR) {

    private var layoutInflater: LayoutInflater? = null

    var mContext: Context

    init {
        mContext = context
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        return when (viewType) {
           TYPE_POST -> {
                // view = LayoutInflater.from(parent.context).inflate(R.layout.feed_list, parent, false)
                //  new PostTypeViewHolder(view).setIsRecyclable(true);
                //  .innerRecyclerView.setRecycledViewPool(viewPool);
                val binding: ListArticlesBinding = DataBindingUtil.inflate(
                    layoutInflater!!,
                    R.layout.list_articles,
                    parent,
                    false
                )



                return ArticleViewHolder(binding)
            }
            TYPE_TEXT -> {
                val bind: ListTextBinding = DataBindingUtil.inflate(
                    layoutInflater!!,
                    R.layout.list_text,
                    parent,
                    false
                )
                //  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_lay, parent, false);
                return TextViewHolder(bind)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is ArticleViewHolder) {
                try {


                    var tempdata = getItem(position)

                        if (tempdata != null) {
                            holder.bindValue(tempdata)
                        }



                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else if(holder is TextViewHolder)
            {
                try {


                    var tempdata = getItem(position)

                    if (tempdata != null) {
                        holder.bindValue(tempdata)
                    }



                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
        }
    }

    companion object {
        const val TYPE_POST = 1
        const val TYPE_TEXT = 2
    }

    inner class ArticleViewHolder(itemView: ListArticlesBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {

        var feedListBinding: ListArticlesBinding? = null


        init {
            feedListBinding = itemView

        }

        fun bindValue(feedback: APIResponse.Article) {

            with(itemView) {
                feedListBinding!!.model = feedback


                itemView.setOnClickListener(View.OnClickListener {
                    v->
                    val budel = Bundle()
                    budel.putParcelable("details",feedback)

                   // val bundle = bundleOf("details" to feedback)
                    v.findNavController().navigate(R.id.action_navigation_home_to_details,budel)
                })


            }


        }
    }


    inner class TextViewHolder(itemView: ListTextBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()) {

        var feedListBinding: ListTextBinding? = null


        init {
            feedListBinding = itemView

        }

        fun bindValue(feedback: APIResponse.Article) {

            with(itemView) {
                feedListBinding!!.model = feedback




            }


        }
    }

    //inside the adapter class



    override fun getItemCount(): Int {
        return super.getItemCount()
    }



    override fun getItemViewType(position: Int): Int {

        return when (getItem(position)!!.urlToImage) {
            getItem(position)!!.urlToImage -> TYPE_POST
            null -> TYPE_TEXT
            else -> -1
        }


        /*return if (getItem(position)) {
            // return TYPE_POST;
            // if (!this.post.get(position-1).getArrayData().get(0).getLink().equalsIgnoreCase("")) {
            TYPE_POST

            // return this.post.get(position-1) != null ? TYPE_POST : VIEW_TYPE_LOADING;
        } else {
            VIEW_TYPE_LOADING
        }*/


        //  return this.post.get(position-1) == null ? VIEW_TYPE_LOADING : TYPE_POST;
    }

   /* override fun getItemCount(): Int {
        return if (post == null) 0 else post!!.size + cutsomViewcount
    }*/


}

val ARTICLES_COMPARATOR = object : DiffUtil.ItemCallback<APIResponse.Article>() {
    override fun areItemsTheSame(oldItem: APIResponse.Article, newItem: APIResponse.Article): Boolean =
        // User ID serves as unique ID
        oldItem.publishedAt == newItem.publishedAt

    override fun areContentsTheSame(oldItem: APIResponse.Article, newItem: APIResponse.Article): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem.equals(newItem)
}