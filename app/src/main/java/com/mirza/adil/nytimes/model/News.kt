package com.mirza.adil.nytimes.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
/**
* The class News used as data model
*
* @author  Mirza Adil
* @email mirzaarslan450@gmail.com
* @version 1.0
* @since 28 jan 2022
*
* This class is data class which holds the data fetching from news- api,
* Note: Only the fields which are relevant according to the project scope is added, rest of all is ignored
*/
@Parcelize
data class News(
    var numResults: Int? = null,
    @SerializedName("results")
    var results: List<Result>? = null,
    @SerializedName("status")
    var status: String? = null
): Parcelable


