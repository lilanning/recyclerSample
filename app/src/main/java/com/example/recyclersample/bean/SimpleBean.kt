package com.example.recyclersample.bean

class SimpleBean{
    private var resId:Int? = null

    private var name:String? = null


    private var imageWidth = 0

    private var imageHeight = 0

    private var showHeight = -1

    fun getResID(): Int? {
        return  resId
    }

    fun getName():String?{
        return  name
    }

    fun setResId(resId:Int?){
        this.resId = resId
    }

    fun setName(name:String?){
        this.name = name
    }

    fun getImageWidth(): Int {
        return imageWidth
    }

    fun setImageWidth(imageWidth: Int) {
        this.imageWidth = imageWidth
    }

    fun getImageHeight(): Int {
        return imageHeight
    }

    fun setImageHeight(imageHeight: Int) {
        this.imageHeight = imageHeight
    }

    fun getShowHeight(): Int {
        return showHeight
    }

    fun setShowHeight(showHeight: Int) {
        this.showHeight = showHeight
    }

    fun isFirst(): Boolean {
        return showHeight == -1
    }
}
