package com.mirza.adil.nytimes.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
/**
* The class Media
*
* @author  Mirza Adil
* @email mirza.madil@gmail.com
* @version 1.0
* @since 28 Jan 2021
*
* This class is data class which holds the data fetching from news- api, specifically holding the media i.e images.
* Note: Only the fields which are relevant according to the project scope is added, rest of all is ignored
*/
@Parcelize
data class MediaMetadata(
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("width")
    var width: Int? = null
):Parcelable