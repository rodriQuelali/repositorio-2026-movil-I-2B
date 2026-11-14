package com.example.myapplicationproyectomvvmsqlite.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FileUtils {
    fun createImageUri(context: Context): Pair<Uri, String> {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = File(context.filesDir, "photos")
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        val authority = "${context.packageName}.fileprovider"
        val uri = FileProvider.getUriForFile(context, authority, imageFile)
        return uri to imageFile.absolutePath
    }

    fun copyUriToAppFile(context: Context, contentUri: Uri): String? {
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val imageFileName = "JPEG_${timeStamp}_"
            val storageDir = File(context.filesDir, "photos")
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }
            val destinationFile = File.createTempFile(imageFileName, ".jpg", storageDir)
            context.contentResolver.openInputStream(contentUri)?.use { input ->
                destinationFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            destinationFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
