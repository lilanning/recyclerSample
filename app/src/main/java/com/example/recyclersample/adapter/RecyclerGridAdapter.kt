package com.example.recyclersample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.recyclersample.R
import com.example.recyclersample.bean.SimpleBean
import com.example.recyclersample.databinding.GridadapterItemBinding

class RecyclerGridAdapter(val context: Context, val datas: List<SimpleBean>, private var onItemClickListener: OnItemClickListener? = null) :
    RecyclerView.Adapter<RecyclerGridAdapter.RecyclerGridAdapterViewHolder>() {

    private val radius = 0

    var screenWidth = context.resources.displayMetrics.widthPixels
    val itemWidth = (screenWidth-20-20- 2*10)/2;
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerGridAdapterViewHolder {
        val gridadapterItemBinding = DataBindingUtil.inflate<GridadapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.gridadapter_item, parent, false
        )
        return RecyclerGridAdapterViewHolder(gridadapterItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerGridAdapterViewHolder, position: Int) {

        val options =  RequestOptions().transform(RoundedCorners(100))
        Glide.with(context).load(datas[position].getResID()).apply(options).into(holder.itemImg)

        holder.itemText.text = datas[position].getName()
        if (datas[position].isFirst()){
            datas[position].setShowHeight(
                datas[position].getImageHeight()*(1.0f*itemWidth/datas[position].getImageWidth()).toInt()
            )
        }
        holder.itemImg.layoutParams.height = datas[position].getImageHeight()


        holder.itemButton.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class RecyclerGridAdapterViewHolder(gridadapterItemBinding: GridadapterItemBinding) : RecyclerView.ViewHolder(gridadapterItemBinding.root) {
        val itemImg = gridadapterItemBinding.gridImage
        val itemText = gridadapterItemBinding.gridText
        val itemButton = gridadapterItemBinding.gridButton

    }

    fun bindClickListener(itemClickListener: OnItemClickListener){
        onItemClickListener = itemClickListener
    }
}