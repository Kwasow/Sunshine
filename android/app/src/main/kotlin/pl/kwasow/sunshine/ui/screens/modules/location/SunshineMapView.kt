package pl.kwasow.sunshine.ui.screens.modules.location

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import org.koin.androidx.compose.koinViewModel
import pl.kwasow.sunshine.R
import pl.kwasow.sunshine.data.UserLocation
import pl.kwasow.sunshine.utils.bitmapDescriptorFromVector

// ====== Public composables
@Composable
fun SunshineMapView(
    hazeState: HazeState,
    paddingValues: PaddingValues,
) {
    val viewModel = koinViewModel<LocationModuleViewModel>()
    val userLocation = viewModel.userLocation.observeAsState()
    val partnerLocation = viewModel.partnerLocation.observeAsState()

    val warsaw = LatLng(52.229845, 21.0104188)
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(warsaw, 10f)
        }

    LaunchedEffect(userLocation.value) {
        val location = userLocation.value ?: return@LaunchedEffect
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude),
                15f,
            ),
        )
    }

    LaunchedEffect(true) {
        viewModel.refreshUserLocation()
    }

    GoogleMap(
        modifier =
            Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
        cameraPositionState = cameraPositionState,
        uiSettings =
            MapUiSettings(
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
            ),
        contentPadding = paddingValues,
    ) {
        val userLoc = userLocation.value
        if (userLoc != null) {
            CurrentLocationMarker(location = userLoc)
        }

        val partnerLoc = partnerLocation.value
        if (partnerLoc != null) {
            PersonMarker(location = partnerLoc)
        }
    }
}

// ====== Private composables
@Composable
private fun CurrentLocationMarker(location: UserLocation) {
    // TODO: Show traditional my location point
    val markerState = rememberMarkerState(position = LatLng(location.latitude, location.longitude))

    Marker(
        state = markerState,
        title = location.userName,
        icon = bitmapDescriptorFromVector(R.drawable.ic_map_marker),
    )
}

@Composable
private fun PersonMarker(location: UserLocation) {
    // TODO: Customize with selected user icon
    val markerState = rememberMarkerState(position = LatLng(location.latitude, location.longitude))

    Marker(
        state = markerState,
        title = location.userName,
        icon = bitmapDescriptorFromVector(R.drawable.ic_map_marker),
    )
}
