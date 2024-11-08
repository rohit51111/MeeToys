package com.bitcodetech.meetoys.ourposts.models


import com.google.gson.annotations.SerializedName

class OurPostResponse (
    @SerializedName("data")
    val ourpost : ArrayList<OurPost>
    )


