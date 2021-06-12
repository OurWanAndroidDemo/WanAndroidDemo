package com.example.wanandroiddemo

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.wanandroiddemo.base.BaseActivity

/**
 * @author taozhu
 * @date 2021/5/30 22:00
 * @description MainActivity
 */
class MainActivity : BaseActivity() {
    override fun initView() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> Toast.makeText(this, "search", Toast.LENGTH_LONG).show();
            R.id.delete -> Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();
            R.id.settings -> Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
        }

        return true
    }

    override fun initData() {}
    override fun initListener() {}
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}