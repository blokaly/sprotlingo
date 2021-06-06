package com.github.blokaly.sprotlingo

import com.github.blokaly.sprotlingo.model.Country
import com.github.blokaly.sprotlingo.model.CountryCrud
import com.github.blokaly.sprotlingo.utils.JsonUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@SpringBootTest
@ExtendWith(SpringExtension::class)
class MongoDataLoader {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate
    @Autowired
    lateinit var countryCrud: CountryCrud

    @Test
    fun testAutowiring() {
        if (mongoTemplate.collectionExists(Country::class.java)) {
            assert(true)
        } else {
            mongoTemplate.createCollection(Country::class.java)
            with(URL("https://restcountries.eu/rest/v2/all").openConnection() as HttpURLConnection) {
                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    response.toString()
                }.run {
                    val countries = JsonUtil.jsonToNode(this)
                    countries.forEach {
                        val country = Country(
                            name = it.get("name").asText(),
                            alpha2Code = it.get("alpha2Code").asText(),
                            alpha3Code = it.get("alpha3Code").asText(),
                            capital = it.get("capital").asText(),
                            region = it.get("region").asText(),
                            subregion = it.get("subregion").asText(),
                            flag = it.get("flag").asText()
                        )
                        countryCrud.insert(country)
                    }
                }
            }
            assert(true)
        }
    }
}