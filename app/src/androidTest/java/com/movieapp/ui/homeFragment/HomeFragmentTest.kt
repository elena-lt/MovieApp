package com.movieapp.ui.homeFragment

import android.view.Gravity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.movieapp.R
import com.movieapp.launchFragmentInHiltContainer
import com.movieapp.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun testLaunchingHomeFragment() {
        launchFragmentInHiltContainer<HomeFragment>()

        onView(withText("Home")).check(matches(isDisplayed()))
    }

}