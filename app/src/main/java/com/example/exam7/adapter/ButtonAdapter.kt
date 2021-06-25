package com.example.exam7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.exam7.R
import com.example.exam7.databinding.ImageButtonItemBinding
import com.example.exam7.databinding.NumberButtonItemBinding

typealias OnButtonClick = (number: String) -> Unit
typealias OnBackClick = () -> Unit


class ButtonAdapter : BaseAdapter<Int>() {
    companion object {
        private const val IMAGE_TYPE = 1
        private const val BUTTON_TYPE = 2
    }

    lateinit var onButtonClick: OnButtonClick
    lateinit var onBackClick: OnBackClick

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Int, ViewBinding> =
        when (viewType) {
            BUTTON_TYPE -> NumberViewHolder(
                NumberButtonItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ImageViewHolder(
                ImageButtonItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: BaseViewHolder<Int, ViewBinding>, position: Int) {
        holder.bind(1)
    }

    override fun getItemCount() = 12

    override fun getItemViewType(position: Int) =
        when (position) {
            9, 11 -> IMAGE_TYPE
            else -> BUTTON_TYPE
        }

    inner class NumberViewHolder(private val binding: NumberButtonItemBinding) :
        BaseViewHolder<Int, NumberButtonItemBinding>(binding) {
        override fun bind(data: Int) {
            if (adapterPosition == 10)
                binding.root.text = "0"
            else
                binding.root.text = "${adapterPosition + 1}"
            binding.root.setOnClickListener {
                onButtonClick(binding.root.text.toString())
            }
        }
    }

    inner class ImageViewHolder(private val binding: ImageButtonItemBinding) :
        BaseViewHolder<Int, ImageButtonItemBinding>(binding) {
        override fun bind(data: Int) {
            when (adapterPosition) {
                9 -> {
                    binding.root.setImageResource(R.drawable.touch)
                }
                11 -> {
                    binding.root.setImageResource(R.drawable.back)
                    binding.root.setOnClickListener {
                        onBackClick()
                    }
                }
            }
        }
    }
}