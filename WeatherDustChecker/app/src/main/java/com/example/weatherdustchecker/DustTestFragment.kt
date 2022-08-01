//package com.example.weatherdustchecker
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import com.fasterxml.jackson.core.JsonParser
//import com.fasterxml.jackson.databind.DeserializationContext
//import com.fasterxml.jackson.databind.JsonNode
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import java.net.URL
//
//@JsonDeserialize(using = MyDust::class)
//data class OpenDustAPIJSONResponse(val pm10, val pm25)
//
//class MyDust : StdDeserializer<OpenDustAPIJSONResponse>(
//    OpenDustAPIJSONResponse::class.java
//) {
//    override fun deserialize(
//        p: JsonParser?,
//        ctxt: DeserializationContext?
//    ): OpenDustAPIJSONResponse {
//        val node = p?.codec?.readTree<JsonNode>(p)
//
//        var id = node?.get("dust")?.elements()?.next()?.get("id")?.asInt()
//        var pm10 = iaqi?.get("pm10")?.get("v")?.asInt()
//        var pm25 = iaqi?.get("pm25")?.get("v")?.asInt()
//
//        return OpenDustAPIJSONResponse(pm10!!, pm25!!)
//    }
//}
//
//class DustTestFragment : Fragment() {
//
//    lateinit var dustImage : ImageView
//    lateinit var statusText1 : TextView
//    lateinit var statusText2 : TextView
//    lateinit var dust1 : TextView
//    lateinit var dust2 : TextView
//    var app_id = "fea33a0ed2c7658fb0ccde7f1d23fdbcd4180ccb"
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater
//            .inflate(R.layout.dust_page_fragment,
//            container, false)
//
//        statusText1 = view.findViewById<TextView>(R.id.dust_1)
//        statusText2 = view.findViewById<TextView>(R.id.dust_2)
//        dust1 = view.findViewById<TextView>(R.id.dust_text1)
//        dust2 = view.findViewById<TextView>(R.id.dust_text2)
//        dustImage = view.findViewById<ImageView>(R.id.dust_icon)
//
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val lat = arguments?.getDouble("lat")
//        val lon = arguments?.getDouble("lon")
//        var url = "http://api.waqi.info/feed/geo:37.58;126.98/?token=fea33a0ed2c7658fb0ccde7f1d23fdbcd4180ccb"
//
//        APICall(object: APICall.APICallback {
//            override fun onComplete(result: String) {
//                Log.d("mytag", result)
//                var mapper = jacksonObjectMapper()
//                var data = mapper?.readValue<OpenDustAPIJSONResponse>(result)
//
//                dust1.text = data.pm10
//                dust2.text = data.pm25
//
////                0~ 50 good
////                51~150 normal
////                151~300 bad
////                300+ very_bad
//
//                val dust1 = data.pm10.toString()
//                val dust2 = data.pm25.toString()
//                if (dust1 <= 50){
//                    dustImage.setImageResource(R.drawable.good)
//                }else if (dust1 <= 150){
//                    dustImage.setImageResource(R.drawable.normal)
//                }else if (dust1 <= 300){
//                    dustImage.setImageResource(R.drawable.bad)
//                }else {
//                    dustImage.setImageResource(R.drawable.very_bad)
//                }
//
//                if (dust2 <= 50){
//                    dustImage.setImageResource(R.drawable.good)
//                }else if (dust2 <= 150){
//                    dustImage.setImageResource(R.drawable.normal)
//                }else if (dust2 <= 300){
//                    dustImage.setImageResource(R.drawable.bad)
//                }else {
//                    dustImage.setImageResource(R.drawable.very_bad)
//                }
//
//
//            }
//        }
//
//        ).execute(URL(url))
//    }
//
//    companion object {
//        fun newInstance(lat: Double, lon: Double)
//            :DustTestFragment
//        {
//            val fragment = DustTestFragment()
//
//            val args = Bundle()
//            args.putDouble("lat", lat)
//            args.putDouble("lon", lon)
//            fragment.arguments = args
//
//            return fragment
//        }
//    }
//
//}