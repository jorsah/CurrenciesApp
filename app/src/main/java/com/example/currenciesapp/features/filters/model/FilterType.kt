package com.example.currenciesapp.features.filters.model

import androidx.annotation.StringRes
import com.example.currenciesapp.R
import com.example.uikit.utills.Strings

enum class FilterType(@StringRes val nameRes: Int) {
    NAME(Strings.filter_name),
    NAME_REVERSED(Strings.filter_name_reversed),
    VALUE(Strings.filter_value),
    VALUE_REVERSED(Strings.filter_value_reversed)
}
