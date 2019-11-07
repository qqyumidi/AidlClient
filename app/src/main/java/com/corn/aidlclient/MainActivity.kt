package com.corn.aidlclient

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.content.ComponentName
import android.content.Context
import android.os.IBinder
import android.content.ServiceConnection
import android.content.Intent
import android.widget.Toast
import com.corn.aidlserver.IMyAidlInterface


class MainActivity : AppCompatActivity() {

    lateinit var iMyAidlInterface: IMyAidlInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            bind()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun bind() {
        var intent: Intent = Intent()
        intent.setAction("com.corn.aidlserver.service")
        intent.setPackage("com.corn.aidlserver")
        bindService(intent, object : ServiceConnection {

            override fun onServiceConnected(name: ComponentName, service: IBinder) {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service)

                Toast.makeText(this@MainActivity, iMyAidlInterface.user.name, Toast.LENGTH_SHORT).show()

            }

            override fun onServiceDisconnected(name: ComponentName) {

            }
        }, Context.BIND_AUTO_CREATE)
    }


}
