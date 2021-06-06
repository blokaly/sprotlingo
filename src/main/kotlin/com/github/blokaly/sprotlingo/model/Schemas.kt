package com.github.blokaly.sprotlingo.model

import com.fasterxml.jackson.databind.JsonNode
import org.joda.time.DateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Country")
@TypeAlias("country")
data class Country(
    @Id val id: String? = null,
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val flag: String,
    @CreatedDate val createdAt: DateTime? = null
)