package com.example.latihan.data

import com.example.latihan.model.Jadwal

object DataJadwal {
    val dummyJadwal = listOf(
        Jadwal(
            id = 1,
            hari = "senin",
            mapel = "BahasaIndonesia",
            waktu = "11 Desember 2023,10:00 WIB"
        ),
        Jadwal(
            id = 2,
            hari = "selasa",
            mapel = "BahasaInggris",
            waktu = "11 Desember 2023,10:00 WIB"
        )
    )
}