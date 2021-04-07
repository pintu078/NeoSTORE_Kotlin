package com.pintu.neostore_kotlin.view.home

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.pintu.neostore_kotlin.R
import com.pintu.neostore_kotlin.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    private lateinit var tabel_CardView: CardView
    private lateinit var mViewPager: ViewPager
    private lateinit var indicator: TabLayout

    private lateinit var headerImg : ImageView
    private lateinit var header_Name : TextView
    private lateinit var header_email : TextView
    private lateinit var notification : TextView


    val num = arrayOf(
        R.drawable.beds,
        R.drawable.sofas,
        R.drawable.cupboards,
        R.drawable.tabels,
        R.drawable.chairs
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        //Menu counter = navigationView.getMenu();
        //notification = (TextView) counter.findItem(R.id.tv_notification);
        //notification = navigationView.menu.findItem(R.id.nav_myCart).actionView as TextView

        val headerContainer = navigationView.getHeaderView(0)
        headerImg = headerContainer.findViewById(R.id.header_img)
        header_Name = headerContainer.findViewById(R.id.header_name)
        header_email = headerContainer.findViewById(R.id.header_email)

        toolbar = findViewById<Toolbar>(R.id.toolbar)

        indicator = findViewById(R.id.indicator)
        mViewPager = findViewById(R.id.viewpager)

        tabel_CardView = findViewById(R.id.tabel_cardView)
        setSupportActionBar(toolbar)


        /*----------------------Navigation Drawer Menu-------------------------*/

//        TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);
//        headerImg.setImageDrawable(drawable);


        /*----------------------Navigation Drawer Menu-------------------------*/
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_myCart)

        imageSliderImplementation()

    }

    private fun imageSliderImplementation() {

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter
        indicator.setupWithViewPager(mViewPager, true)
        
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_myCart -> {
                Toast.makeText(this, "MyCart", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_Table -> {
                Toast.makeText(this, "Table", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_myAccount -> {
                Toast.makeText(this, "My Account", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_myOrders -> {
                Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_location -> {
                Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}