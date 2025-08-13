package com.app.lingotales.util

import com.app.lingotales.R
import com.app.lingotales.presentation.choose.CategoryType
import com.app.lingotales.presentation.home.PagerItem

object MockBooks {

    fun titleFor(type: CategoryType): String = when (type) {
        CategoryType.MASAL -> "Masal Kitapları"
        CategoryType.EGITICI -> "Eğitici Kitaplar"
        CategoryType.HIKAYELER -> "Hikayeler"
        CategoryType.HAYVAN_HIKAYELERI -> "Hayvan Hikayeleri"
    }


    private val hikayeler = listOf(
        PagerItem("Sefiller", "Victor Hugo", R.drawable.book_cover_1),
        PagerItem("Suç ve Ceza", "Fyodor Dostoyevski", R.drawable.book_cover_2),
        PagerItem("1984", "George Orwell", R.drawable.book_cover_5),
        PagerItem("Hayvan Çiftliği", "George Orwell", R.drawable.book_cover_6),
        PagerItem("Bülbülü Öldürmek", "Harper Lee", R.drawable.book_cover_7),
        PagerItem("Kürk Mantolu Madonna", "Sabahattin Ali", R.drawable.book_cover_8),
        PagerItem("Tutunamayanlar", "Oğuz Atay", R.drawable.book_cover_9),
        PagerItem("Sefiller (Tekrar)", "Victor Hugo", R.drawable.book_cover_1),
        PagerItem("1984 (Tekrar)", "George Orwell", R.drawable.book_cover_5)
    )

    private val masal = listOf(
        PagerItem("Toprak ve Uçan Tohum", "Toprak ve Uçan Tohum", R.drawable.kapak_toprak_ve_ucan_thoum),
        PagerItem("Rapunzel", "Grimm Kardeşler", R.drawable.book_cover_2),
        PagerItem("Pamuk Prenses", "Grimm Kardeşler", R.drawable.book_cover_5)
    )

    private val egitici = listOf(
        PagerItem("Bilim Nedir?", "Carl Sagan", R.drawable.book_cover_6),
        PagerItem("Matematikle Tanışıyorum", "Çocuk Serisi", R.drawable.book_cover_7),
        PagerItem("Doğa Güncesi", "Keşif Kitabı", R.drawable.book_cover_8)
    )

    private val hayvanHikayeleri = listOf(
        PagerItem("Martı", "Richard Bach", R.drawable.book_cover_9),
        PagerItem("Beyaz Diş", "Jack London", R.drawable.book_cover_1),
        PagerItem("Küçük Kara Balık", "Samed Behrengi", R.drawable.book_cover_2)
    )

    fun forType(type: CategoryType): List<PagerItem> = when (type) {
        CategoryType.MASAL -> masal
        CategoryType.EGITICI -> egitici
        CategoryType.HIKAYELER -> hikayeler
        CategoryType.HAYVAN_HIKAYELERI -> hayvanHikayeleri
    }
}