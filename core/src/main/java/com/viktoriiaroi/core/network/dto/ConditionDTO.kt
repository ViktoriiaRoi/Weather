package com.viktoriiaroi.core.network.dto

import com.google.gson.annotations.SerializedName

class ConditionDTO(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null,
)
