package com.mytaxy.test.android.screens.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.mytaxy.test.R
import com.mytaxy.test.android.screens.base.MvvmActivity
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.entities.FleetType
import com.mytaxy.test.entities.Poi
import com.mytaxy.test.util.extensions.toLatLong
import com.mytaxy.test.injection.ActivityComponent

class MapActivity : MvvmActivity<MapViewModel>(), OnMapReadyCallback {

    override val viewModel: MapViewModel by lazy {
        viewModelFactory.get<MapViewModel>(this)
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setupViews()
        setupViewModel()
    }

    private fun setupViews() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setupViewModel() {
        val selectedPoi = intent?.extras?.getParcelable<Poi>(POI_KEY)
        viewModel.updateSelectedPoi(selectedPoi)
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnCameraIdleListener {
            val bounds = map.projection.visibleRegion.latLngBounds
            viewModel.onBoundsChanged(bounds)
        }

        intent?.extras?.getParcelable<Poi>(POI_KEY)?.let {
            val coordinate = it.coordinate.toLatLong()
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, DEFAULT_ZOOM))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewModelStateChanged(state: ViewModelState) {
        when (state) {
            ViewModelState.Loading -> updateLoading(true)
            is ViewModelState.Data<*> -> updateData(state.data as List<Poi>)
            is ViewModelState.Error -> handleError()
        }
    }

    private fun updateData(data: List<Poi>) {
        updateLoading(false)
        map.clear()
        data.forEach {
            val marker = buildMarker(it)
            map.addMarker(marker)
        }
    }

    private fun handleError() {
        updateLoading(false)
        Toast.makeText(this, R.string.generic_error_message, Toast.LENGTH_LONG).show()
    }

    private fun updateLoading(isLoading: Boolean) {

    }

    private fun buildMarker(it: Poi): MarkerOptions {
        val coordinate = it.coordinate.toLatLong()
        val image = if (it.fleetType == FleetType.POOLING) {
            R.drawable.ic_map_pooling
        } else {
            R.drawable.ic_map_taxi
        }
        val icon = BitmapDescriptorFactory.fromResource(image)
        return MarkerOptions()
            .position(coordinate)
            .rotation(it.heading.toFloat())
            .flat(true)
            .anchor(0.5f, 0.5f)
            .icon(icon)
    }

    companion object {

        private const val POI_KEY = "POI_KEY"
        private const val DEFAULT_ZOOM = 16f

        fun launch(context: Context, poi: Poi?) {
            val intent = Intent(context, MapActivity::class.java)
            poi?.let {
                intent.putExtra(POI_KEY, it)
            }
            context.startActivity(intent)
        }

    }

}
