package com.mirza.adil.nytimes.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mirza.adil.nytimes.model.Media
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
/**
* The class Result used in [News]
*
* @author  Mirza Adil
* @email mirzaarslan450@gmail.com
* @version 1.0
* @since 28 Mar 2021
*
* This class is data class which holds the data fetching from news-api,
* Note: Only the fields which are relevant according to the project scope is added, rest of all is ignored
*/
@Parcelize
data class Result(
    @SerializedName("abstract")
    var description: String? = null,
    @SerializedName("byline")
    var byline: String? = null,
    @SerializedName("media")
    var media: List<Media>? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("updated")
    var updated: String? = null,
    @SerializedName("type")
    var type: String? = null,
    ):Parcelable