package com.github.blokaly.sprotlingo.model

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository("CountryDao")
interface CountryDao : MongoRepository<Country, String> {
}