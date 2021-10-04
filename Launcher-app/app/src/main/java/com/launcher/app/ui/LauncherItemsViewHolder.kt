package com.launcher.app.ui

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.launcher.app.data.AppInfo
import com.launcher.app.databinding.LauncherItemsBinding

class LauncherItemsViewHolder(private val binding: LauncherItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appInfo: AppInfo, onEnabledAppClicked: (name: String) -> Unit) {
        setBackgroundColor(appInfo)
        binding.root.isEnabled = appInfo.isEnabled
        binding.app.text = appInfo.packageLabel
        binding.icon.setImageDrawable(appInfo.packageIcon)
        binding.root.setOnClickListener {
            onEnabledAppClicked(appInfo.packageName)
        }
    }

    private fun setBackgroundColor(appInfo: AppInfo) {
        if (appInfo.isEnabled.not()) {
            binding.itemRoot.setBackgroundColor(Color.GRAY)
        } else {
            binding.itemRoot.setBackgroundColor(Color.WHITE)
        }
    }
}