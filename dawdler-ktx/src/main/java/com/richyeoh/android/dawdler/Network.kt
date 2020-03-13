package com.richyeoh.android.dawdler

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.RequestBody

private val gson: Gson = Gson()

fun Map<String, String?>.toReqBody(): RequestBody {
    return toRequestBody()
}

fun Map<String, String?>.toRequestBody(): RequestBody {
    val obj = JsonObject()
    for ((key, value) in this) {
        obj.addProperty(key, value)
    }
    val json = gson.toJson(obj)
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
}
