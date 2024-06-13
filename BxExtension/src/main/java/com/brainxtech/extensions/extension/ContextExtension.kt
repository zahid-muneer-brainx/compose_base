package com.brainxtech.extensions.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ZERO
import com.brainxtech.extensions.utils.Constants.ZERO_LONG


fun Context.openApp(packageName: String) {
    packageManager.getLaunchIntentForPackage(packageName)?.apply {
        startActivity(this)
    }
}

fun Context.openDeviceHomeScreen(){
    val intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_HOME)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}

fun Context.getCurrentAppVersionName(): String {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, ZERO)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        EMPTY_STRING
    }
}

fun Context.getCurrentAppVersionCode(): Long {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, ZERO)
        packageInfo.longVersionCode
    } catch (e: PackageManager.NameNotFoundException) {
        ZERO_LONG
    }
}