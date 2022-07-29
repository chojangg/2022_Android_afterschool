package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import com.example.lotteryapp.R

class MainActivity : AppCompatActivity() {
    lateinit var currentNums: String    // 처음에 값을 대입을 안해도 됨

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SharedPreference 객체를 가져오기
        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)

        val lottoNumView = findViewById<TextView>(R.id.lotto_num)
        currentNums = generateRandomLottoNum(6, "-")
        lottoNumView.text = currentNums

        val generateNumberBtn = findViewById<Button>(R.id.gen_num)
        generateNumberBtn.setOnClickListener {
            currentNums = generateRandomLottoNum(6, "-")
            lottoNumView.text = currentNums
        }

        val saveNumberBtn = findViewById<Button>(R.id.save_num)
        saveNumberBtn.setOnClickListener {
            var lottoNums = pref.getString("lottonums", "")
            var numList = if (lottoNums == "") {
                mutableListOf<String>()
            } else {
                lottoNums!!.split(",").toMutableList()
            }

            numList.add(currentNums)
            Log.d("mytag", numList.size.toString())
            Log.d("mytag", numList.toString())

            val editor = pref.edit()
            editor.putString("lottonums", numList.joinToString(","))
            editor.apply()

        }
        findViewById<Button>(R.id.num_list).setOnClickListener{
            val intent = Intent(this, LotteryNumListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.check_num).setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("https://dhlottery.co.kr/gameResult.do?method=byWin"))
            startActivity(intent)
        }
    }

    fun generateRandomLottoNum(count:Int, sep: String): String{
        val nums = mutableListOf<Int>()
        for(i in 1..count) nums.add((1..45).random())
        return nums.joinToString(sep)
    }

}


