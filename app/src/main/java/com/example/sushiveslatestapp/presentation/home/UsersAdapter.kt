package com.example.sushiveslatestapp.presentation.home.services

import android.graphics.Rect
import android.graphics.drawable.Drawable
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
import com.example.sushiveslatestapp.databinding.CardviewUserBinding
import com.example.sushiveslatestapp.domain.entitys.home.Users
import com.example.sushiveslatestapp.presentation.dpToPx

class UsersAdapter(
    private var items: List<Users> = listOf(),
    private val onClickButton: () -> Unit,
    private val onClickUsers: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_USER = 0
        private const val TYPE_BUTTON = 1
    }

    fun setListUsers(newItems: List<Users>) {
        val lastSize = items.size + 1
        val newSize = newItems.size
        items = newItems.toList()
        if (newSize > lastSize) {
            notifyItemRangeInserted(lastSize, newSize)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_USER -> {
                val view = layoutInflater.inflate(R.layout.cardview_user, parent, false)
                UsersViewHolder(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.button, parent, false)
                object : RecyclerView.ViewHolder(view) {
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UsersViewHolder -> {
                holder.bind(items[position - 1])
                holder.itemView.setOnClickListener {
                    onClickUsers(position - 1)
                }
            }
            else -> {
                holder.itemView.setOnClickListener {
                    onClickButton()
                }
            }
        }
    }

    override fun getItemCount(): Int = (items.size + 1)

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_BUTTON
        } else {
            TYPE_USER
        }
    }
}

class UsersViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardviewUserBinding.bind(view)

    fun bind(user: Users) {
        binding.usersName.text = user.nameUser
        Glide.with(view).load(user.urlImage).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .apply(RequestOptions.circleCropTransform())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.usersImage.setImageResource(R.drawable.image_second_user)
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
            }).into(binding.usersImage)
    }
}

class UsersItemDecoration() : RecyclerView.ItemDecoration() {

    companion object {
        private const val OFFSET = 10
    }

    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            rect.right = // Add space/"padding" on right side
                when (childAdapterPosition) {
                    0 -> 0
                    (adapter.itemCount - 1) -> v.marginRight
                    else -> OFFSET.dpToPx(v.context).toInt()
                }
        }
    }
}