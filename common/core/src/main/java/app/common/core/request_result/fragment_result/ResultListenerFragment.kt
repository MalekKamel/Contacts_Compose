package app.common.core.request_result.fragment_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResultListener

internal class ResultListenerFragment : Fragment() {
    private lateinit var callback: (Bundle) -> Unit
    var requestKey: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(requestKey) { _, bundle ->
            callback(bundle)
            remove()
        }
    }

    companion object {
        private fun newInstance(): ResultListenerFragment {
            return ResultListenerFragment()
        }

        fun request(
            requestKey: String,
            activity: FragmentActivity,
            callback: (Bundle) -> Unit
        ): ResultListenerFragment {
            val frag = newInstance()
            frag.requestKey = requestKey
            frag.callback = callback
            activity.supportFragmentManager
                .beginTransaction()
                .add(frag, ResultListenerFragment::class.java.simpleName)
                .commitAllowingStateLoss()
            return frag
        }
    }

    fun remove() {
        val fragment = activity?.supportFragmentManager?.findFragmentByTag(ResultListenerFragment::class.java.name) ?: return
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(fragment)
            ?.commit()
    }
}