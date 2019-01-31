package com.android.kotlinmvvmdemo.view.feeds

import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.kotlinmvvmdemo.R
import com.android.kotlinmvvmdemo.data.model.Row
import com.android.kotlinmvvmdemo.util.gone
import com.android.kotlinmvvmdemo.util.invisible
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedsAdapter(private val items: MutableList<Row>) :
    RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_feed, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items[position].title != null) {
            holder.mTitle?.visibility = VISIBLE
            holder.mTitle?.text = items[position].title
        } else {
            holder.mTitle?.visibility = GONE
        }

        if (items[position].description != null) {
            holder.mDesc?.visibility = VISIBLE
            holder.mDesc?.text = items[position].description
        } else {
            holder.mDesc?.visibility = GONE
        }

        var requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.error_robot)
            .error(R.drawable.error_robot)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
//        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(12))

        if (items[position].imageHref != null) {
            holder.mImagePoster?.visibility = VISIBLE
            holder.mNxt?.visibility = VISIBLE
            holder.mImagePoster?.let {
                Glide.with(it.context)
                    .load(items[position].imageHref)
                    .apply(RequestOptions.centerCropTransform())
                    .listener(object : RequestListener<Drawable> {
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            holder.mProgressBar?.invisible()
                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            holder.mImagePoster?.invisible()
                            holder.mProgressBar?.invisible()
                            return false
                        }
                    })
                    .into(it)
            }
        } else {
            holder.mImagePoster?.visibility = GONE
            holder.mNxt?.visibility = GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        internal val mImagePoster: ImageView? by lazy {
            itemView.findViewById(R.id.imgPoster) as ImageView?
        }

        internal val mNxt: ImageView? by lazy {
            itemView.findViewById(R.id.nxt) as ImageView?
        }

        internal val mTitle: AppCompatTextView? by lazy {
            itemView.findViewById(R.id.txtTitle) as AppCompatTextView?
        }

        internal val mDesc: AppCompatTextView? by lazy {
            itemView.findViewById(R.id.txtDesc) as AppCompatTextView?
        }

        internal val mProgressBar: ProgressBar? by lazy {
            itemView.findViewById(R.id.progress_circular) as ProgressBar?
        }

    }
}