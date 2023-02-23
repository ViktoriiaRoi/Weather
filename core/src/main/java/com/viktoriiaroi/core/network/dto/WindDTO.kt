package com.viktoriiaroi.core.network.dto

import com.google.gson.annotations.SerializedName

class WindDTO(
    @SerializedName("speed") var speed: Double? = null,
    @SerializedName("deg") var deg: Int? = null,
    @SerializedName("gust") var gust: Double? = null,
)