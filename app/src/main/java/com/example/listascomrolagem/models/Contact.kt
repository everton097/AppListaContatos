package com.example.listascomrolagem.models

import androidx.annotation.DrawableRes
import com.example.listascomrolagem.R

data class Contact(
    @DrawableRes val picture: Int = R.drawable.baseline_face_24,
    val name: String = "",
    val status: String = "",
)
