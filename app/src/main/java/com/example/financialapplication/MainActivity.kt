package com.example.financialapplication

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import com.example.financialapplication.ui.items.ItemsFragment

class MainActivity : SimpleNavActivity() {

    override val defaultEnterAnim: Int = androidx.appcompat.R.anim.abc_slide_in_bottom
    override val defaultExitAnim: Int = androidx.appcompat.R.anim.abc_fade_out
    override val defaultPopEnterAnim: Int = androidx.appcompat.R.anim.abc_slide_in_top
    override val defaultPopExitAnim: Int = androidx.appcompat.R.anim.abc_fade_out

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(ItemsFragment())
        }
    }

}