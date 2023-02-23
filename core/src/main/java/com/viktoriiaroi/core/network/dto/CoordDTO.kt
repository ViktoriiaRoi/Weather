package com.viktoriiaroi.core.network.dto

import com.google.gson.annotations.SerializedName

class CoordDTO (
    @SerializedName("lon" ) var lon : Double? = null,
    @SerializedName("lat" ) var lat : Double? = null
)
