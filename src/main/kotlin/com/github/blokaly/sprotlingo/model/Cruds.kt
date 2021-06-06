package com.github.blokaly.sprotlingo.model

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class CountryCrud(@Qualifier("CountryDao") private val dao: CountryDao) :
    CountryDao by dao