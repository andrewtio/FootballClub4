package com.andrew.associate.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        -Modul Hello Kotlin-
//        val list = findViewById<RecyclerView>(R.id.club_list)

        initData()

//        -Modul Hello Kotlin-
//        list.layoutManager = LinearLayoutManager(this)
//        list.adapter = RecyclerViewAdapter(this, items){
        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = RecyclerViewAdapter(this, items) {
//            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
//            toast.show()
            onClickListener(it)
        }
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val description = resources.getStringArray(R.array.club_description)
        items.clear()
        for (i in name.indices){
            items.add(Item(name[i],
                image.getResourceId(i, 0), description[i]))
        }
        // Recycle the typed array
        image.recycle()
    }

    private fun onClickListener(items : Item){
        startActivity<DetailActivity>(DetailActivity.NAME to items.name, DetailActivity.BADGE to items.image, DetailActivity.DESCRIPTION to items.description)
    }

}
