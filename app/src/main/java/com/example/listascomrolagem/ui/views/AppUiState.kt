package com.example.listascomrolagem.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.example.listascomrolagem.R

data class AppUiState(
    @StringRes val title: Int = R.string.contact_list,
    @DrawableRes val fabIcon : Int = R.drawable.baseline_add_24,
    @StringRes val iconContentDescription: Int = R.string.insert_new_contact,
)
