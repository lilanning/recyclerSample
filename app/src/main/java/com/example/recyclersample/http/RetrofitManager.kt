package com.example.recyclersample.http

import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.math.log

object RetrofitManager {

    private val TAG = "RetrofitManager"
    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        //创建动态代理返回给service
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(getOkHttpClient()).baseUrl("https://api.uomg.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

            .build()
    }

    /**
     * get同步请求
     * @param url string   网络接口
     */
    fun getUrl(url: String) {
        Thread {
            try {
                val request = Request.Builder().url(url).build() //构造请求体
                val call = getOkHttpClient().newCall(request) //构造请求对象
                val response = call.execute() //发起同步请求
//                Log.d(TAG, "getUrl: ${response.body?.string()} ")

                val gson = Gson().fromJson<Data>(response.body?.string(),Data::class.java)
                Log.d(TAG, "getUrl: gson: $gson")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG, "getUrl: ${e.message}")
            }
        }.start()

    }


    /**
     * get异步请求
     * @param url string
     */
    fun getAsyncUrl(url: String) {
//        val headers: Map<String, String> = HashMap()
//        val appcode = "6a9b3f06cd3b46c593af74936b9d400e"
//        ( headers as HashMap<String, String>)["Authorization"] = "APPCODE$appcode"

        try {
            val request = Request.Builder().url(url).build() //构造请求体
            val call = getOkHttpClient().newCall(request) //构造请求对象
            //发起异步请求
            val response = call.enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d(TAG, "onFailure: ${e.message}")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        Log.d(TAG, "getUrl: ${response.body?.string()} ")


                    }

                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "getUrl: ${e.message}")
        }
    }

    /**
     * post同步请求
     *
     */
    fun post(url: String) {
        val body = FormBody.Builder().add("code", "1").add("message", "existence")
            .add("ae_url", "https:\\/\\/url.cn\\/5V2IwUr")
            .add("longurl", "http:\\/\\/api.ututxi79.cn\\/5560?RnOqjTk.css").build()
        val request = Request.Builder().url(url).post(body).build()
        Thread {
            val response = getOkHttpClient().newCall(request).execute()
            Log.d(TAG, "post: ${response.body?.string()} ")
        }.start()

    }

    fun postAsync(url: String) {
        val body = FormBody.Builder().add("code", "1").add("message", "existence")
            .add("ae_url", "https:\\/\\/url.cn\\/5V2IwUr")
            .add("longurl", "http:\\/\\/api.ututxi79.cn\\/5560?RnOqjTk.css").build()
        val request = Request.Builder().url(url).post(body).build()
        getOkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "onResponse: ${response.body?.string()}")
            }

        })

    }


    /**
     * 需要支持文件上传的接口
     */
    fun postAsyncMultipart(url: String) {
        val file = File(Environment.getExternalStorageDirectory(), "1.png")
        if (!file.exists()) {
            return
        }
        val body = MultipartBody.Builder().addFormDataPart("key", "value")
            .addFormDataPart("key1", "value1")
            .addFormDataPart(
                "file",
                "file.png",
                RequestBody.create("application/octet-stream".toMediaTypeOrNull(), file)
            ).build()
        val request = Request.Builder().url(url).post(body).build()
        getOkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
            }

        })
    }

    /**
     * 异步提交字符串
     */
    fun postAsyncString(url: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("key1","value1")
        jsonObject.addProperty("key2","value2")
        val body = jsonObject.toString().toRequestBody("application/json;charset=urf-8".toMediaTypeOrNull())
        val request = Request.Builder().url(url).post(body).build()
        getOkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
            }
        })
    }



}

