package com.a.wanandroid.net

import okhttp3.Cache
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * Created by chenxz on 2018/4/21.
 */
object RetrofitHelper {

    private var retrofit: Retrofit? = null

    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")  // baseUrl
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())

                .build()
        }
        return retrofit
    }

    /**
     * 获取 OkHttpClient
     */
     fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
//        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }

        builder.run {
//            addInterceptor(httpLoggingInterceptor)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            hostnameVerifier { _, _ -> true }
            setSSLSocketFactory(this)
        }
        return builder.build()
    }

    private fun setSSLSocketFactory(okhttpBuilder: OkHttpClient.Builder) {

        val x509TrustManager = object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
        val trustAllCerts = arrayOf<TrustManager>(x509TrustManager)
        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            sslContext.socketFactory?.let { okhttpBuilder.sslSocketFactory(it, x509TrustManager) }

        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

}