package app.common.core.extension

import android.os.Bundle
import java.util.HashMap

fun Bundle.toStringMap(): Map<String, String> {
    return bundleToStringMap(this)
}

fun bundleToStringMap(bundle: Bundle): Map<String, String> {
    val map: MutableMap<String, String> = HashMap()
    val ks = bundle.keySet()
    val iterator: Iterator<String> = ks.iterator()
    while (iterator.hasNext()) {
        val key = iterator.next()
        map[key] = bundle.get(key).toString()
    }
    return map
}