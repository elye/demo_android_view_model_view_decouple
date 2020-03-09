package com.elyeproj.decouplingarchitecture

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.elyeproj.decouplingarchitecture.MainActivity.Companion.globalContext
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var persistedText: String = String()
        lateinit var globalContext: Context

        fun isGlobalContextInitialized() = ::globalContext.isInitialized
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isGlobalContextInitialized()) {
            globalContext = this.baseContext
        }

        btn_no_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorCyan))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, BasicArchitectureActivity::class.java))
            }
        }

        btn_delegate_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorYellow))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, DelegateArchitectureActivity::class.java))
            }
        }

        btn_functional_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorLightGray))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, FunctionalArchitectureActivity::class.java))
            }
        }

        btn_notification_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorPurple))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, NotificationArchitectureActivity::class.java))
            }
        }

        btn_reactive_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorOrange))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ReactiveArchitectureActivity::class.java))
            }
        }

        btn_fullreactive_architecture.run {
            setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorGreen))
            setOnClickListener {
                startActivity(Intent(this@MainActivity, UltimateReactiveArchitectureActivity::class.java))
            }
        }
    }
}
