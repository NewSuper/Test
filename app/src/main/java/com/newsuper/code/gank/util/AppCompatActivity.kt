package com.newsuper.code.gank.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.newsuper.code.R

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int, tag: String) {
    supportFragmentManager.transact {
        replace(frameId, fragment, tag)
    }
}

fun AppCompatActivity.hideKeyboard() {
    val view = currentFocus
    if (view != null) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun AppCompatActivity.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val option =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun AppCompatActivity.setupToolBar(toolbar: Toolbar, action: ActionBar.() -> Unit) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        action()
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom,
                R.anim.abc_fade_out,
                R.anim.abc_fade_in,
                R.anim.abc_shrink_fade_out_from_bottom
        )
        action()
    }.commit()
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, ViewModeFactory.getInstance(application)).get(viewModelClass)