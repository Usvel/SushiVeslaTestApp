package com.example.sushiveslatestapp.presentation.home

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.sushiveslatestapp.R
import com.example.sushiveslatestapp.databinding.CardviewServiceBinding
import com.example.sushiveslatestapp.domain.entitys.home.Services
import com.example.sushiveslatestapp.presentation.dpToPx

class ServicesAdapter(
    private var items: List<Services> = listOf(),
    private val onClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ServicesViewHolder>() {

    fun setListUsers(newItems: List<Services>) {
        val lastSize = items.size
        val newSize = newItems.size - 1
        items = newItems.toList()
        if (newSize > lastSize) {
            notifyItemRangeInserted(lastSize, newSize)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cardview_service, parent, false)
        return ServicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = items.size
}

class ServicesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardviewServiceBinding.bind(view)

    fun bind(service: Services) {
        binding.serviceName.text = service.nameService
        Glide.with(view).load(service.urlImage).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .apply(RequestOptions.circleCropTransform())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.serviceImage.setImageResource(R.drawable.ic_airplane)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(binding.serviceImage)
    }
}

class ServicesItemDecoration() :
    RecyclerView.ItemDecoration() {

    companion object {
        private const val OFFSET_HORISONTAL = 13
        private const val OFFSET_BOTTOM = 10
    }

    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            v.foregroundGravity = Gravity.CENTER
            rect.right = // Add space/"padding" on right side
                if ((childAdapterPosition + 1) % 4 == 0) {
                    Log.d("Adapter", childAdapterPosition.toString())
                    0
                } else {
                    OFFSET_HORISONTAL.dpToPx(v.context).toInt()
                }
            rect.left = OFFSET_BOTTOM.dpToPx(v.context).toInt()
            rect.bottom = if (childAdapterPosition < adapter.itemCount - 5) {
                10.dpToPx(v.context).toInt()
            } else {
                0
            }
        }
    }
}