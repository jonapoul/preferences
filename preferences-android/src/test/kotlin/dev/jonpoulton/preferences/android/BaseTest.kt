package dev.jonpoulton.preferences.android

import android.content.Context
import androidx.annotation.CallSuper
import androidx.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@Suppress("UnnecessaryAbstractClass")
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
abstract class BaseTest {
  protected lateinit var androidSharedPreferences: AndroidSharedPreferences

  @CallSuper
  @Before fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    androidSharedPreferences = AndroidSharedPreferences(sharedPreferences)
  }
}
