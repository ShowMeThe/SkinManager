package com.showmethe.skinmanager.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class DataBindingViewHolder<T : ViewDataBinding>(var binding: T) : RecyclerView.ViewHolder(binding.root)