package com.example.currenciesapp.features.main.utills

import com.example.currenciesapp.features.filters.model.FilterType
import com.example.currenciesapp.features.main.model.RateUI

fun List<RateUI>.sortBy(filterType: FilterType) =
    when (filterType) {
        FilterType.NAME -> sortedBy { it.toCurrency }
        FilterType.NAME_REVERSED -> sortedByDescending { it.toCurrency }
        FilterType.VALUE -> sortedBy { it.value }
        FilterType.VALUE_REVERSED -> sortedByDescending { it.value }
    }
