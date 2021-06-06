package com.github.blokaly.sprotlingo.utils

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.*

object JsonUtil {
    private val objectMapper = ObjectMapper().apply {

        // sort by letter
        configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true)
        // when map is serialization, sort by key
        configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        // ignore mismatched fields
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        // use field for serialize and deSerialize
        setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
        setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
    }

    fun jsonToNode(json: String): JsonNode =  objectMapper.readTree(json)
}