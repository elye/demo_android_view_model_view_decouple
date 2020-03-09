package com.elyeproj.decouplingarchitecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var persistedText: String = String()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_no_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorCyan))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, NoArchitectureActivity::class.java))
            }
        }
    }
}
