package com.example.listascomrolagem.ui.views

import androidx.annotation.DrawableRes
import com.example.listascomrolagem.R

data class InsertFormUiState(
    @DrawableRes val picture: Int = R.drawable.baseline_face_24,
    val name: String = "",
    val status: String = "",
)
