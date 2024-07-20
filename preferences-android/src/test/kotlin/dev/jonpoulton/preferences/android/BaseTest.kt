package dev.jonpoulton.preferences.android

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.CallSuper
import androidx.preference.PreferenceManager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
abstract class BaseTest {
  private lateinit var context: Context
  private lateinit var sharedPreferences: SharedPreferences
  protected lateinit var androidSharedPreferences: AndroidSharedPreferences

  @CallSuper
  @Before fun setup() {
    context = ApplicationProvider.getApplicationContext()
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    androidSharedPreferences = AndroidSharedPreferences(sharedPreferences)
  }
}
