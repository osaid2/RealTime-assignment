package com.example.realtime

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = Firebase.database
        val myRef = database.getReference()

        save.setOnClickListener {
        var name = PersonName.text.toString()
        var number = PersonNumber.text.toString()
        var address = PersonAddress.text.toString()

        val person = hashMapOf(
            "name" to "$name",
            "number" to "$number",
            "address" to "$address"

        )

        myRef.child("person").child("$id").setValue(person)
            id++
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
    }
        show.setOnClickListener {

            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    textData.text = value.toString()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Faild", Toast.LENGTH_SHORT).show()

                }

            })
        }
    }
}