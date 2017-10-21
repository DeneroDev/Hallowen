package com.example.denero.halloween

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.graphics.Typeface
import android.media.MediaPlayer
import android.widget.TextView


class MainActivity: AppCompatActivity() {
    var pumpinImg:ImageView ?= null
    var candyImg:ImageView ?= null
    var animationPump:Animation ?= null
    var animationTitle:Animation ?= null
    var animationContent:Animation ?= null
    var animationCandy:Animation ?= null
    lateinit var titleFont:Typeface
    lateinit var contentFont:Typeface
    lateinit var titleText:TextView
    lateinit var contentText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        startAnim()
        playHorrorMusic()
    }

    fun init(){
        //Шрифты
        titleFont = Typeface.createFromAsset(assets, "nightmarefont.otf")
        contentFont = Typeface.createFromAsset(assets, "outlastfont.otf")
        //Поиск view
        titleText = findViewById(R.id.text_title) as TextView
        contentText= findViewById(R.id.content_text) as TextView
        pumpinImg = findViewById(R.id.pumpImg) as ImageView
        candyImg = findViewById(R.id.candy_img) as ImageView
        //Установка шрифтов за textview
        titleText.setTypeface(titleFont)
        contentText.setTypeface(contentFont)
        //Загрузка анимаций
        animationTitle = AnimationUtils.loadAnimation(this, R.anim.title_anim)
        animationContent = AnimationUtils.loadAnimation(this, R.anim.content_anim)
        animationPump = AnimationUtils.loadAnimation(this, R.anim.pump_anim)
        animationCandy = AnimationUtils.loadAnimation(this, R.anim.candy_anim)
    }
    fun startAnim()
    {
        //Запуск анимаций
        pumpinImg!!.startAnimation(animationPump)
        candyImg!!.startAnimation(animationCandy)
        titleText.startAnimation(animationTitle)
        contentText.startAnimation(animationContent)
    }

    fun playHorrorMusic(){
        //Запуск музыки в отдельном потоке
        object : Thread() {
            override fun run() {
                super.run()
                var mp = MediaPlayer.create(applicationContext, R.raw.horror);
                mp.start();
            }
        }.start()
    }

}
