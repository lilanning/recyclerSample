package com.example.recyclersample.waveview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import android.os.Handler
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.TintTypedArray
import com.example.recyclersample.R
import java.util.*

/**
 * @author [Jenly](mailto:jenly1314@gmail.com)
 */
class WaveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 画笔
     */
    private var mPaint: Paint? = null

    /**
     * 波纹数
     */
    private var mWaveCount = 1

    /**
     * 波纹颜色
     */
    private var mWaveColor = 0x3F09B9D2

    /**
     * 着色器
     */
    private var mShader: Shader? = null

    /**
     * 波纹振幅
     */
    private var mWaveAmplitude = 0f

    /**
     * 波纹宽
     */
    private var mWaveWidth = 0

    /**
     * 波纹高
     */
    private var mWaveHeight = 0

    /**
     * 波纹X轴偏移最大速度
     */
    private var mMaxSpeed =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, displayMetrics)

    /**
     * 波纹X轴偏移最小速度
     */
    private var mMinSpeed =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, displayMetrics)

    /**
     * 波纹X轴偏移速度
     */
    private lateinit var mOffsetSpeeds: FloatArray

    /**
     * 波纹X轴起始偏移量
     */
    private lateinit var mOffsets: IntArray

    /**
     * 波纹Y轴位置
     */
    private lateinit var mPositions: FloatArray

    /**
     * 波纹周期因素ω， 最小正周期：T=2π/ω（其中ω必须>0）
     */
    private var mWaveCycleW = 0f

    /**
     * 是否执行动画
     */
    private var isAnim = true

    /**
     * 刷新间隔时间
     */
    private var mRefreshInterval = 15

    /**
     * 随机
     */
    private var mRandom: Random? = null

    /**
     * 刷新视图
     */
    private val REFRESH_VIEW = 1

    /**
     * 是否倒置
     */
    private var mIsInverted = false

    /**
     * 是否竖立
     */
    private var mIsVertical = false

    /**
     * 波纹方向，默认从右到左
     */
    private var mDirection = DIRECTION.LEFT_TO_RIGHT

    enum class DIRECTION(val mValue: Int) {
        LEFT_TO_RIGHT(1), RIGHT_TO_LEFT(2);

        companion object {
            fun getFromInt(value: Int): DIRECTION {
                for (direction in values()) {
                    if (direction.mValue == value) {
                        return direction
                    }
                }
                return LEFT_TO_RIGHT
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun init(context: Context, attrs: AttributeSet?) {
        mRandom = Random()
        val displayMetrics = displayMetrics
        mWaveAmplitude = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, displayMetrics)
        val a = TintTypedArray.obtainStyledAttributes(
            context, attrs,
            R.styleable.WaveView
        )

        mWaveCount = a.getInt(R.styleable.WaveView_waveCount, mWaveCount)
        mWaveColor = a.getColor(R.styleable.WaveView_waveColor, mWaveColor)
        mWaveAmplitude = a.getDimension(R.styleable.WaveView_waveAmplitude, mWaveAmplitude)
        mMaxSpeed = a.getDimension(R.styleable.WaveView_waveMaxSpeed, mMaxSpeed)
        mMinSpeed = a.getDimension(R.styleable.WaveView_waveMinSpeed, mMinSpeed)
        mRefreshInterval = a.getInt(R.styleable.WaveView_waveRefreshInterval, mRefreshInterval)
        isAnim = a.getBoolean(R.styleable.WaveView_waveAutoAnim, isAnim)
        mIsInverted = a.getBoolean(R.styleable.WaveView_waveInverted, mIsInverted)
        mDirection =
            DIRECTION.getFromInt(a.getInt(R.styleable.WaveView_waveDirection, mDirection.mValue))
        mIsVertical = a.getBoolean(R.styleable.WaveView_waveVertical, mIsVertical)



        a.recycle()

        mPaint = Paint()
        updatePaint()
        mOffsetSpeeds = FloatArray(mWaveCount)
        //初始化速度
        for (i in 0 until mWaveCount) {
            mOffsetSpeeds[i] = randomSpeed()
        }
        mOffsets = IntArray(mWaveCount)
    }

    private val displayMetrics: DisplayMetrics
        private get() = resources.displayMetrics

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //TypedValue.applyDimension把非标准尺寸转换成标准尺寸, 如: dp->px:
        val defaultWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, displayMetrics)
                .toInt()
        val defaultHeight =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, displayMetrics)
                .toInt()
        val width =
            measureHandler(widthMeasureSpec, if (mIsVertical) defaultHeight else defaultWidth)
        val height =
            measureHandler(heightMeasureSpec, if (mIsVertical) defaultWidth else defaultHeight)
        setMeasuredDimension(width, height)
    }

    /**
     * 测量处理
     * @param measureSpec
     * @param defaultSize
     * @return
     */
    private fun measureHandler(measureSpec: Int, defaultSize: Int): Int {
        var result = defaultSize
        val measureMode = MeasureSpec.getMode(measureSpec)
        val measureSize = MeasureSpec.getSize(measureSpec)
        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize
        } else if (measureMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, measureSize)
        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWaveWidth = w
        mWaveHeight = h
        //波长为总宽度或高度
        val waveLength = if (mIsVertical) mWaveHeight else mWaveWidth
        //根据波纹数初始化波纹的偏移量
        for (i in 0 until mWaveCount) {
            mOffsets[i] = waveLength / mWaveCount * i
        }

        //Sin函数周期 ω = 2π/T
        mWaveCycleW = (2 * Math.PI / waveLength).toFloat()
        mPositions = FloatArray(waveLength)

        //正弦曲线  y = Asin(ωx + φ)
        for (i in 0 until waveLength) {
            mPositions[i] = (mWaveAmplitude * Math.sin((mWaveCycleW * i).toDouble())).toFloat()
        }
    }

    /**
     * 更新画笔
     */
    private fun updatePaint() {
        mPaint!!.reset()
        // 去除画笔锯齿
        mPaint!!.isAntiAlias = true
        // 设置风格为实线
        mPaint!!.style = Paint.Style.FILL
        if (mShader != null) { //不为空则设置着色器支持渐变
            mPaint!!.shader = mShader
        } else { //设置纯色
            mPaint!!.color = mWaveColor
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawWave(canvas)
    }

    /**
     * 绘制波纹
     * @param canvas
     */
    private fun drawWave(canvas: Canvas) {
        if (mWaveCount > 0) {
            val waveLength = if (mIsVertical) mWaveHeight else mWaveWidth
            //遍历波纹数
            for (i in 0 until mWaveCount) {
                for (j in 0 until waveLength) {
                    val offset = mOffsets[i]
                    //根据偏移量绘制波纹线
                    if (mIsVertical) { //纵向
                        if (mIsInverted) { //是否倒置
                            canvas.drawLine(
                                0f,
                                j.toFloat(),
                                mWaveWidth - mWaveAmplitude - mPositions[(offset + j) % waveLength],
                                j.toFloat(),
                                mPaint!!
                            )
                        } else {
                            canvas.drawLine(
                                mWaveAmplitude - mPositions[(offset + j) % waveLength],
                                j.toFloat(),
                                mWaveWidth.toFloat(),
                                j.toFloat(),
                                mPaint!!
                            )
                        }
                    } else { //横向
                        if (mIsInverted) { //是否倒置
                            canvas.drawLine(
                                j.toFloat(),
                                0f,
                                j.toFloat(),
                                mWaveHeight - mWaveAmplitude - mPositions[(offset + j) % waveLength],
                                mPaint!!
                            )
                        } else {
                            canvas.drawLine(
                                j.toFloat(),
                                mWaveAmplitude - mPositions[(offset + j) % waveLength],
                                j.toFloat(),
                                mWaveHeight.toFloat(),
                                mPaint!!
                            )
                        }
                    }
                }
                //根据方向来进行偏移
                if (mDirection == DIRECTION.LEFT_TO_RIGHT) {
                    mOffsets[i] = (mOffsets[i] - mOffsetSpeeds[i]).toInt()
                    //偏移
                    //超过周期宽度则重置
                    if (mOffsets[i] <= 0) {
                        mOffsets[i] = waveLength
                        //一个周期后，随机一个速度
                        mOffsetSpeeds[i] = randomSpeed()
                    }
                } else {
                    //偏移
                    mOffsets[i] = (mOffsets[i] + mOffsetSpeeds[i]).toInt()
                    //超过周期宽度则重置
                    if (mOffsets[i] >= waveLength) {
                        mOffsets[i] = 0
                        //一个周期后，随机一个速度
                        mOffsetSpeeds[i] = randomSpeed()
                    }
                }
            }
            if (isAnim) { //是否显示动画，延迟刷新视图
                mHandler.removeMessages(REFRESH_VIEW)
                mHandler.sendEmptyMessageDelayed(REFRESH_VIEW, mRefreshInterval.toLong())
                //                postInvalidateDelayed(mRefreshInterval);
            }
        }
    }

    private val mHandler = Handler(Handler.Callback { msg ->
        when (msg.what) {
            REFRESH_VIEW -> {
                invalidate()
                return@Callback true
            }
        }
        false
    })

    /**
     * 随机一个速度
     * @return
     */
    private fun randomSpeed(): Float {
        return if (mMinSpeed != mMaxSpeed) {
            random(mMinSpeed, mMaxSpeed)
        } else mMaxSpeed
    }

    /**
     * 随机一个范围内的数
     * @param min
     * @param max
     * @return
     */
    private fun random(min: Float, max: Float): Float {
        return mRandom!!.nextFloat() * (max - min) + min
    }

    /**
     * 开始动画
     */
    fun start() {
        if (!isAnim) {
            isAnim = true
            invalidate()
        }
    }

    /**
     * 停止动画
     */
    fun stop() {
        if (isAnim) {
            isAnim = false
            invalidate()
        }
    }

    override fun onDetachedFromWindow() {
        mHandler.removeMessages(1)
        isAnim = false
        super.onDetachedFromWindow()
    }

    /**
     * 设置波纹颜色
     * @param waveColor
     */
    fun setWaveColor(waveColor: Int) {
        mWaveColor = waveColor
        mShader = null
        updatePaint()
        invalidate()
    }

    /**
     * 设置波纹颜色
     * @param resId
     */
    fun setWaveColorResource(resId: Int) {
        val color = resources.getColor(resId)
        setWaveColor(color)
    }

    /**
     * 设置波纹着色器
     * @param shader
     */
    fun setWaveShader(shader: Shader?) {
        mShader = shader
        updatePaint()
        invalidate()
    }

    /**
     * 设置波纹最大速度
     * @param maxSpeed
     */
    fun setMaxSpeed(maxSpeed: Float) {
        mMaxSpeed = maxSpeed
    }

    /**
     * 设置波纹最大速度
     * @param minSpeed
     */
    fun setMinSpeed(minSpeed: Float) {
        mMinSpeed = minSpeed
    }

    /**
     * 设置波纹最大速度
     * @param maxSpeed
     */
    fun setMaxSpeedDP(maxSpeed: Float) {
        mMinSpeed = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, maxSpeed, displayMetrics)
    }

    /**
     * 设置波纹最大速度
     * @param minSpeed
     */
    fun setMinSpeedDp(minSpeed: Float) {
        mMinSpeed = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, minSpeed, displayMetrics)
    }

    /**
     * 设置波纹最大速度
     * @param resIds
     */
    fun setMaxSpeedResource(resIds: Int) {
        mMaxSpeed = resources.getDimension(resIds)
    }

    /**
     * 设置波纹最小速度
     * @param resIds
     */
    fun setMinSpeedResource(resIds: Int) {
        mMinSpeed = resources.getDimension(resIds)
    }

    /**
     * 设置刷新间隔时间(保证每秒刷新超过60帧，让肉眼无感知卡顿现象)
     * @param refreshInterval 默认15ms  帧率(fps) = 1秒钟/刷新间隔时间
     */
    fun setRefreshInterval(refreshInterval: Int) {
        mRefreshInterval = refreshInterval
    }

    init {
        init(context, attrs)
    }
}