package com.maderajan.muni.lyricsseach.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO 2.3 RetrofitUtil
object RetrofitUtil {

    private const val LYRICS_BASE_URL = "https://api.lyrics.ovh/v1/"
    private const val DEEZER_BASE_URL = "https://api.deezer.com/"

    fun createLyricsService(): LyricsWebService =
        create(LYRICS_BASE_URL, createOkHttpClient())

    fun createDeezerService(): DeezerWebService =
        create(DEEZER_BASE_URL, createOkHttpClient())

    private inline fun <reified T> create(baseUrl: String, okHttpClient: OkHttpClient): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(T::class.java)

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            addInterceptor(logging)
        }.build()
}