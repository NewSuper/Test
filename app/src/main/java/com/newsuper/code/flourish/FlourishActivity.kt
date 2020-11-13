package com.newsuper.code.flourish


import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.newsuper.code.R
import kotlinx.android.synthetic.main.activity_flourish.*
import kotlinx.android.synthetic.main.activity_flourish.recyclerView
import kotlinx.android.synthetic.main.activity_flourish.view.*
import kotlinx.android.synthetic.main.layout_flourish_main.*
import kotlinx.android.synthetic.main.toolbar_custom.*
import kotlinx.android.synthetic.main.toolbar_custom.toolbar_more
import kotlinx.android.synthetic.main.toolbar_custom.view.*

class FlourishActivity : AppCompatActivity() {

    private val adapter by lazy { FeedAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flourish)

        val flourish: Flourish<LinearLayout> = createFlourish(parentLayout) {
            setFlourishLayout(R.layout.layout_flourish_main)
            setFlourishAnimation(FlourishAnimation.BOUNCE)
            setFlourishOrientation(FlourishOrientation.TOP_LEFT)
            setShowOnStart(true)
        }

        flourish.flourishView.toolbar_title.text = "Profile"
        flourish.flourishView.toolbar_more.setOnClickListener {
            flourish.dismiss { Toast.makeText(this, "dismissed", Toast.LENGTH_SHORT).show() }
        }
        flourish.flourishView.recyclerView.adapter = adapter

        toolbar_title.text = "Timeline"
        toolbar_more.setOnClickListener {
            flourish.show { Toast.makeText(this, "showed", Toast.LENGTH_SHORT).show() }
        }

        tabLayout.addTab(tabLayout.newTab().setText("Timeline"))
        tabLayout.addTab(tabLayout.newTab().setText("Contents"))

        recyclerView.adapter = adapter
        adapter.addItem(FeedItem(ContextCompat.getDrawable(this, R.drawable.gift2), "skydoves",
                getString(R.string.lesson3)))
        adapter.addItem(
                FeedItem(ContextCompat.getDrawable(this, R.drawable.gift2), "The Little Prince",
                        getString(R.string.lesson4)))
        adapter.addItem(FeedItem(ContextCompat.getDrawable(this, R.drawable.gift2), "Night night",
                getString(R.string.lesson5)))
    }
}