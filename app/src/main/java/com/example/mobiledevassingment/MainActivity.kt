package com.example.mobiledevassingment
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    // Register the launcher for camera permission result
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mainactivity)

        // Get references to the icons
        val cameraIcon = findViewById<ImageView>(R.id.camera_icon)
        val searchIcon = findViewById<ImageView>(R.id.search_icon)
        val menuIcon = findViewById<ImageView>(R.id.menu_icon)

        // Set click listener for the camera icon
        cameraIcon.setOnClickListener {
            // Check if camera permission is granted
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                // Request camera permission
                requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }

        searchIcon.setOnClickListener {
            // Handle search icon click
        }

        // Set click listener for the menu icon
        menuIcon.setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun openCamera() {
        // Intent to open the camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            startActivity(cameraIntent)
        } else {
            Toast.makeText(this, "No camera application found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPopupMenu(view: View) {
        // Create a PopupMenu
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.new_group -> {
                    Toast.makeText(this, "New Group clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.new_broadcast -> {
                    Toast.makeText(this, "New Broadcast clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.linked_devices -> {
                    Toast.makeText(this, "Linked Devices clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.starred_messages -> {
                    Toast.makeText(this, "Starred Messages clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings -> {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
