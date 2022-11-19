package com.example.recyclersample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R
import com.example.recyclersample.databinding.LayoutItemBinding

class SimpleAdapter(val context: Context,val datas:List<SimpleBean>) : RecyclerView.Adapter<SimpleAdapter.SimpleAdapterViewHolder>() {




    private var onItemClickListener:OnItemClickListener? = null



    class SimpleAdapterViewHolder(binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root){
        val itemPosition = binding.itemPosition
        val itemImg = binding.itemImg
        val itemDescription = binding.itemDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleAdapterViewHolder {
        val itemBinding = DataBindingUtil.inflate<LayoutItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_item, parent, false
        )

        return SimpleAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SimpleAdapterViewHolder, position: Int) {
        holder.itemPosition.text = "${position+1}"

        holder.itemImg.setImageDrawable(
            datas[position].getResID()
            ?.let { ResourcesCompat.getDrawable(context.resources, it,null) })

        holder.itemDescription.text = datas[position].getName()
        holder.itemView.setOnClickListener {
           onItemClickListener?.onItemClick(position)
        }


    }

    override fun getItemCount(): Int {
      return datas.size
    }

    fun bindClickListener(itemClickListener: OnItemClickListener){
        this.onItemClickListener = itemClickListener
    }
}