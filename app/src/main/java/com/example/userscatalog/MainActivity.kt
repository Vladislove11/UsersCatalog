package com.example.userscatalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolBarTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listView: ListView

    val listUsers: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)
        listView = findViewById(R.id.listView)

        toolBarTB = findViewById(R.id.toolBarTB)
        setSupportActionBar(toolBarTB)
        title = "Каталог пользователей"

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listUsers)
        listView.adapter = adapter

        saveBTN.setOnClickListener {
            var age = ageET.text.toString().toIntOrNull()
            if(age == null) age = 0

            listUsers.add(User(nameET.text.toString(), age))
            adapter.notifyDataSetChanged()
            nameET.text.clear()
            ageET.text.clear()
        }

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val note = adapter.getItem(position)
                adapter.remove(note)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitCM ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}