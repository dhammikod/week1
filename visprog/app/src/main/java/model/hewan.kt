package model

import android.os.Parcel
import android.os.Parcelable

class hewan(
    var namahewan: String?,
    var jenishewan: String?,
    var usiahewan: Int,
    var imageuri: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namahewan)
        parcel.writeString(jenishewan)
        parcel.writeInt(usiahewan)
        parcel.writeString(imageuri)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<hewan> {
        override fun createFromParcel(parcel: Parcel): hewan {
            return hewan(parcel)
        }

        override fun newArray(size: Int): Array<hewan?> {
            return arrayOfNulls(size)
        }
    }
}