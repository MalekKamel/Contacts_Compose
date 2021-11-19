package app.common.core.request_result.fragment_result

import android.os.Bundle

interface FragmentResultKeyType {
    val value: String
}

interface FragmentResultParamType {
    val value: String
}

class FragmentResultParamBuilder {
    val bundle = Bundle()

    fun param(key: FragmentResultParamType, value: Bundle) {
        bundle.putBundle(key.value, value)
    }

    fun param(key: FragmentResultParamType, value: Array<Bundle>) {
        value.forEach {
            bundle.putBundle(key.value, it)
        }
    }

    fun param(key: FragmentResultParamType, value: Double) {
        bundle.putDouble(key.value, value)
    }

    fun param(key: FragmentResultParamType, value: Long) {
        bundle.putLong(key.value, value)
    }

    fun param(key: FragmentResultParamType, value: String) {
        bundle.putString(key.value, value)
    }
}