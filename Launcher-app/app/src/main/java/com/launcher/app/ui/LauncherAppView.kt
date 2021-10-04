package com.launcher.app.ui

import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.launcher.app.data.AppInfo
import com.launcher.app.databinding.ActivityLauncherBinding
import javax.inject.Inject

interface LauncherAppView {
    fun render(binding: ActivityLauncherBinding, viewState: LauncherAppViewState, context: Context)
    fun filter(query: String)
}

class LauncherAppViewImpl @Inject constructor(
    private val packageManager: PackageManager
) : LauncherAppView {
    private lateinit var adapter: LauncherItemsAdapter
    override fun render(binding: ActivityLauncherBinding, viewState: LauncherAppViewState, context: Context) {
        when(viewState) {
            LauncherAppViewState.Initial -> { }
            is LauncherAppViewState.Error -> {
                binding.loader.visibility = View.GONE
            }
            LauncherAppViewState.Loading -> {
                binding.loader.visibility = View.VISIBLE
            }
            is LauncherAppViewState.Success -> {
                binding.loader.visibility = View.GONE
                val layoutManager = LinearLayoutManager(binding.root.context)
                adapter = LauncherItemsAdapter(viewState.apps as ArrayList<AppInfo>) { name ->
                    val launchIntent = packageManager.getLaunchIntentForPackage(name)
                    if (launchIntent != null) {
                        context.startActivity(launchIntent)
                    } else {
                        Toast.makeText(context, "Package : $name not found", Toast.LENGTH_LONG).show()
                    }
                }
                binding.launcherRecyclerView.layoutManager = layoutManager
                binding.launcherRecyclerView.adapter = adapter
            }
        }
    }

    override fun filter(query: String) {
        adapter.filter.filter(query)
    }
}