package com.example.latihan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jadwal(
    val id:Int,
    val hari:String,
    val mapel:String,
    val waktu:String
):Parcelable
