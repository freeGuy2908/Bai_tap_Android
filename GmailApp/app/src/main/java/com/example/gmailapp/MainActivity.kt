package com.example.gmailapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = ArrayList<Model>()

        itemList.add(Model(R.drawable.e, "Edurila.com", "\$19 Only (First 10 spots) - Bestselling...\n" +
                "Are you looking to Learn Web Designin...", "12:34 PM"))

        itemList.add(Model(R.drawable.c, "Chris Abad", "Help make Campaign Monitor better\n" +
                "Let us know your thoughts! No Images...", "11:22 AM"))

        itemList.add(Model(R.drawable.t, "Tuto.com", "8h de formation gratuite et les nouvea...\n" +
                "Photoshop, SEO, Blender, CSS, WordPre...", "11:04 AM"))

        itemList.add(Model(R.drawable.s, "support", "Société Ovh : suivi de vos services - hp...\n" +
                "SAS OVH - http://www.ovh.com 2 rue K...", "10:26 AM"))

        itemList.add(Model(R.drawable.m, "Matt from lonic", "The New lonic Creator Is Here!\n" +
                "Announcinq the all-new Creator, build...", "09:34 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "08:56 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "08:44 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "08:20 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "07:56 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "07:44 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "07:20 PM"))

        itemList.add(Model(R.drawable.t, "Tuan Pham", "Hello there\n" +
                "Nice to see you", "08:56 PM"))

        val listView = findViewById<RecyclerView>(R.id.rview)
        listView.layoutManager = LinearLayoutManager(this)
        listView.setAdapter(MyAdapter(itemList))
    }
}