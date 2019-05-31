package com.mytaxy.test.android.screens.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mytaxy.test.R
import com.mytaxy.test.android.screens.base.MvvmActivity
import com.mytaxy.test.android.screens.base.ViewModelState
import com.mytaxy.test.injection.ActivityComponent
import com.mytaxy.test.util.ClickHandler
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_error_layout.*

class HomeActivity : MvvmActivity<HomeViewModel>(),
    ClickHandler<HomeModelItem> {

    override val viewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupViews()
        viewModel.fetchData()
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    private fun setupViews() {
        retryButton.setOnClickListener { viewModel.fetchData() }
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = HomeAdapter(this)
        recyclerView.adapter = adapter
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewModelStateChanged(state: ViewModelState) {
        when (state) {
            is ViewModelState.Data<*> -> showData(state.data as HomeModel)
            is ViewModelState.Error -> showErrorState(state.throwable)
            ViewModelState.Loading -> showLoadingState()
        }
    }

    private fun showData(data: HomeModel) {
        errorLayout.visibility = View.GONE
        loadingProgressBar.visibility = View.GONE
        toolBar.title = data.title
        adapter.dataSource = data.items
    }

    private fun showErrorState(throwable: Throwable) {
        loadingProgressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }

    private fun showLoadingState() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    override fun onClick(item: HomeModelItem) {

    }

}
