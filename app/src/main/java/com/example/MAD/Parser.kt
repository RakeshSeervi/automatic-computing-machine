package com.example.MAD

import android.os.Bundle
import android.util.Xml
import androidx.appcompat.app.AppCompatActivity
import com.example.MAD.databinding.ActivityParserBinding
import com.google.android.material.tabs.TabLayout
import org.json.JSONObject
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

class Parser : AppCompatActivity() {
    lateinit var binding: ActivityParserBinding
    private val jsonObjectFromXML = JSONObject()
    lateinit var jsonObjectFromJSON: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Parser"

        binding = ActivityParserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseJson(assets.open("data.json"))
        parseXML(assets.open("data.xml"))
        display(jsonObjectFromJSON)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "JSON" -> display(jsonObjectFromJSON)
                    else -> display(jsonObjectFromXML)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    fun display(jsonObject: JSONObject) {
        binding.apply {
            cityName.text = jsonObject["CityName"].toString()
            latitude.text = jsonObject["Latitude"].toString()
            longitude.text = jsonObject["Longitude"].toString()
            temperature.text = jsonObject["Temperature"].toString()
            humidity.text = jsonObject["Humidity"].toString()
        }
    }

    private fun parseJson(inputStream: InputStream) {
        inputStream.use {
            val jsonByteArray = ByteArray(inputStream.available())
            inputStream.read(jsonByteArray)
            jsonObjectFromJSON = JSONObject(String(jsonByteArray))
        }
    }

    private fun parseXML(inputStream: InputStream) {
        inputStream.use {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(it, null)
            parser.nextTag()
            readFeed(parser)
        }
    }

    private fun readFeed(parser: XmlPullParser) {
        parser.next()
        while (parser.nextTag() != XmlPullParser.END_TAG) {
            val tagName = parser.name
            parser.next()
            jsonObjectFromXML.put(tagName, parser.text)
            parser.nextTag()
            parser.next()
        }
    }
}