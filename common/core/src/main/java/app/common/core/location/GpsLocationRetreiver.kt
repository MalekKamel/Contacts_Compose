package app.common.core.location

import android.content.Context
import android.location.LocationManager
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest

class GpsLocationRequester(val activity: FragmentActivity) {

    fun request(
        locationRequest: LocationRequest,
        completion: () -> Unit = {} //TODO:
    ) {
        if (isGpsEnabled())
            return

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val client = LocationServices.getSettingsClient(activity)
        val task = client.checkLocationSettings(builder.build())

        task.addOnFailureListener {
            if (it is ResolvableApiException)
                it.startResolutionForResult(activity, 0)
        }

        task.addOnSuccessListener { }
    }

    private fun isGpsEnabled(): Boolean {
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}