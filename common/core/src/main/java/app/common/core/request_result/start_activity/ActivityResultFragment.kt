package app.common.core.request_result.start_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

internal class ActivityResultFragment : Fragment() {
    private lateinit var callback: (ActivityResult) -> Unit

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Intent>
    lateinit var requestedIntent: Intent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val value = it
            callback(value)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchPermissions()
    }

    private fun launchPermissions() {
        requestPermissionLauncher.launch(requestedIntent)
    }

    companion object {
        private fun newInstance(): ActivityResultFragment {
            return ActivityResultFragment()
        }

        fun request(intent: Intent,
                    activity: FragmentActivity,
                    callback: (ActivityResult) -> Unit): ActivityResultFragment {
            var frag = findFragment(activity.supportFragmentManager)
            if (frag == null) {
                frag = newInstance()
                frag.requestedIntent = intent
                frag.callback = callback
                activity.supportFragmentManager
                        .beginTransaction()
                        .add(frag, ActivityResultFragment::class.java.simpleName)
                        .commitAllowingStateLoss()
                return frag
            }
            frag.requestedIntent = intent
            frag.callback = callback
            frag.launchPermissions()
            return frag
        }

        private fun findFragment(fragmentManager: FragmentManager): ActivityResultFragment? {
            return fragmentManager.findFragmentByTag(ActivityResultFragment::class.java.simpleName) as ActivityResultFragment?
        }

    }
}