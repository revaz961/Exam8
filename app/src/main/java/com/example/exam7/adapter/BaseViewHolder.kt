package com.example.exam7.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<D, out VB : ViewBinding>(binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(data: D)
}