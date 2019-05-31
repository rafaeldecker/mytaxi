package com.mytaxy.test.android.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mytaxy.test.R
import com.mytaxy.test.util.ClickHandler
import kotlinx.android.synthetic.main.view_holder_poi.view.*

/**
 * Created by Rafael Decker on 2019-05-30.
 */

class HomeAdapter(
    private var clickHandler: ClickHandler<HomeModelItem>? = null
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var dataSource: List<HomeModelItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(
        itemView: View,
        private var clickHandler: ClickHandler<HomeModelItem>? = null
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(poi: HomeModelItem) {
            itemView.setOnClickListener {
                clickHandler?.onClick(poi)
            }

            itemView.titleTextView.setText(poi.titleResId)
            itemView.idTextView.text = String.format(itemView.context.getString(R.string.id), poi.id)
            itemView.iconBackgroundView.setBackgroundResource(poi.backgroundResId)
            itemView.iconImageView.setImageResource(poi.iconResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_poi, parent, false)
        return ViewHolder(view, clickHandler)
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataSource[position])
}
