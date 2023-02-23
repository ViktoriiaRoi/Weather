package com.viktoriiaroi.core.network.dto

import com.google.gson.annotations.SerializedName

class WeatherDTO(
    @SerializedName("coord") var coord: CoordDTO? = CoordDTO(),
    @SerializedName("weather") var conditions: List<ConditionDTO> = listOf(),
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: MainDTO? = MainDTO(),
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("wind") var wind: WindDTO? = WindDTO(),
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("sys") var sys: SysDTO? = SysDTO(),
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Int? = null,
)