package com.example.recyclerviewexample.model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rover(
    val id: Int,
    val name: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rover> {
        override fun createFromParcel(parcel: Parcel): Rover {
            return Rover(parcel)
        }

        override fun newArray(size: Int): Array<Rover?> {
            return arrayOfNulls(size)
        }
    }
}