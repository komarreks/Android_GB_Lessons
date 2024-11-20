package com.example.recyclerviewexample.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Photo(
    val id: Long,
    val sol: Long,
    val camera: Camera?,
    val img_src: String,
    val earth_date: String,
    val rover: Rover?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readParcelable(Camera::class.java.classLoader),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable(Rover::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(sol)
        parcel.writeParcelable(camera, flags)
        parcel.writeString(img_src)
        parcel.writeString(earth_date)
        parcel.writeParcelable(rover, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}