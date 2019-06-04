package com.mytaxi.test.android.screens.map

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
import com.mytaxi.test.R
import com.mytaxi.test.android.screens.base.MvvmActivity
import com.mytaxi.test.android.screens.base.ViewModelState
import com.mytaxi.test.entities.Poi
import com.mytaxi.test.util.extensions.toLatLong
import com.mytaxi.test.injection.ActivityComponent
import com.mytaxi.test.util.extensions.toBounds
import com.mytaxi.test.util.extensions.visibleOrGone
import kotlinx.android.synthetic.main.activity_maps.*

class MapActivity : MvvmActivity<MapViewModel>(), OnMapReadyCallback {

    override val viewModel: MapViewModel by lazy {
        viewModelFactory.get<MapViewModel>(this)
    }

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        setupViews()
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    private fun setupViews() {
        toolBar.setNavigationOnClickListener { finish() }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setOnCameraIdleListener {
            val bounds = map.projection.visibleRegion.latLngBounds
            viewModel.onBoundsChanged(bounds.toBounds())
        }
        val selectedPoi = intent?.extras?.getParcelable<Poi>(POI_KEY)
        viewModel.onMapReady(selectedPoi)
        selectedPoi?.let {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(it.coordinate.toLatLong(), DEFAULT_ZOOM))
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewModelStateChanged(state: ViewModelState) {
        when (state) {
            ViewModelState.Loading -> updateLoading(true)
            is ViewModelState.Data<*> -> updateData(state.data as List<MapModel>)
            is ViewModelState.Error -> handleError()
        }
    }

    private fun updateData(data: List<MapModel>) {
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
        loadingProgressBar.visibility = isLoading.visibleOrGone()
    }

    private fun buildMarker(it: MapModel): MarkerOptions {
        val icon = BitmapDescriptorFactory.fromResource(it.carImageResId)
        return MarkerOptions()
            .position(it.coordinate)
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
