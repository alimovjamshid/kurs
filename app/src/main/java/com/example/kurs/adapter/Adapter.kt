package com.example.kurs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kurs.databinding.ItemMarvelBinding
import com.example.kurs.retrofit.models.Data
import com.squareup.picasso.Picasso

class Adapter(private val listener: OnItemDataClick) :
    ListAdapter<Data, Adapter.Vh>(MydiffUtill()) {
    inner class Vh(var itemMarvelBinding: ItemMarvelBinding) :
        RecyclerView.ViewHolder(itemMarvelBinding.root) {

        fun onBind(marvelData: Data) {
            itemMarvelBinding.apply {
                val substring = marvelData.Ccy.substring(0, 2).lowercase()
                Picasso.get().load("https://flagcdn.com/w160/${substring}.png").into(image)
                name.text = marvelData.CcyNm_UZ
                quentity.text = marvelData.Rate
            }
            itemView.setOnClickListener {
                listener.onItemClick(marvelData)
            }
        }
    }

    class MydiffUtill : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == oldItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemDataClick {
        fun onItemClick(marvelData: Data)
    }

}
