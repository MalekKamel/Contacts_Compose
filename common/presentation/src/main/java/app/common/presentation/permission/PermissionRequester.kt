package app.common.presentation.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class PermissionRequester(private val activity: FragmentActivity) {

    fun request(vararg permissions: String, callback: (Boolean) -> Unit) {
        when {
            hasPermissions(*permissions) -> callback(true)
            else -> PermissionFrag.request(permissions.asList(), activity, callback)
        }
    }

    fun hasPermissions(vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    companion object {
        fun isGranted(permission: String, context: Context): Boolean {
            return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
    }
}