package com.example.restoapp

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.restoapp.databinding.ActivityBleScanBinding


class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private var isScanning = false

    // Initializes Bluetooth adapter.
    private var bluetoothAdapter: BluetoothAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bluetoothAdapter = getSystemService(BluetoothManager::class.java).adapter


        startBleIfPossible()


        binding.blePlayimg.setOnClickListener {
            togglePlayPause()
        }
    }

    private fun startBleIfPossible() {
        when {
            !checkifBLE() || bluetoothAdapter == null -> {
                Toast.makeText(this, "ble_not_supported", Toast.LENGTH_SHORT).show()
            }
            !(bluetoothAdapter?.isEnabled ?: false) -> {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSION_LOCATION
                    )
                }
                else ->
                {
                    //youpi
                }
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            startBleIfPossible()
        }
    }


    private fun checkifBLE(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)
    }

    private fun togglePlayPause() {
        isScanning = !isScanning
        if (isScanning) {
            binding.bleScanTitle.text = getString(R.string.bleScanPauseTitle)
            binding.blePlayimg.setImageResource(R.drawable.ic_pause)
            binding.progressBar.isVisible = true
        } else {
            binding.bleScanTitle.text = getString(R.string.bleScanTitle)
            binding.blePlayimg.setImageResource(R.drawable.ic_play)
            binding.progressBar.isVisible = false
        }
    }

    companion object {
        const private val REQUEST_ENABLE_BT = 33
        const private val REQUEST_PERMISSION_LOCATION = 22

    }
}