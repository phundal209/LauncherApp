package com.launcher.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.launcher.app.data.AppInfo
import com.launcher.app.databinding.LauncherItemsBinding
import java.util.*
import kotlin.collections.ArrayList

internal class LauncherItemsAdapter(private val appInfoList: ArrayList<AppInfo>,
                                    private val onEnabledAppClicked: (name: String) -> Unit)
    : RecyclerView.Adapter<LauncherItemsViewHolder>(), Filterable {
    private var filteredAppInfoList: ArrayList<AppInfo> = ArrayList(appInfoList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherItemsViewHolder {
        val view: LauncherItemsBinding = LauncherItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LauncherItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LauncherItemsViewHolder, position: Int) {
        holder.bind(filteredAppInfoList[position], onEnabledAppClicked)
    }

    override fun getItemCount(): Int = filteredAppInfoList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterPattern: String = constraint.toString().toLowerCase(Locale.ROOT).trim()
                filteredAppInfoList = if (filterPattern.isEmpty()) {
                    appInfoList
                } else {
                    val resultList: ArrayList<AppInfo> = ArrayList()
                    appInfoList.forEach { appInfo ->
                        if(appInfo.packageLabel.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            resultList.add(appInfo)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredAppInfoList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredAppInfoList = results?.values as ArrayList<AppInfo>
                notifyDataSetChanged()
            }

        }
    }
}