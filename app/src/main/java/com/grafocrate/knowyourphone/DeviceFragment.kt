package com.grafocrate.knowyourphone


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.grafocrate.knowyourphone.databinding.FragmentDeviceBinding


class DeviceFragment : Fragment() {

    private var _binding: FragmentDeviceBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { DeviceViewModelFactory(requireContext()) }
    private val viewModel: DeviceViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeviceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deviceInfo.observe(viewLifecycleOwner, Observer { deviceInfo ->
            binding.modelTextView.text = "Model: ${deviceInfo.model}"
            binding.manufacturerTextView.text = "Manufacturer: ${deviceInfo.manufacturer}"
            binding.brandTextView.text = "Brand: ${deviceInfo.brand}"
            binding.boardTextView.text = "Board: ${deviceInfo.board}"
            binding.hardwareTextView.text = "Hardware: ${deviceInfo.hardware}"
            binding.screenSizeTextView.text = "Screen Size: ${deviceInfo.screenSize}"
            binding.screenResolutionTextView.text = "Screen Resolution: ${deviceInfo.screenResolution}"
            binding.screenDensityTextView.text = "Screen Density: ${deviceInfo.screenDensity}"
            binding.totalRamTextView.text = "Total RAM: ${deviceInfo.totalRam}"
            binding.availableRamTextView.text = "Available RAM: ${deviceInfo.availableRam}"
            binding.internalStorageTextView.text = "Internal Storage: ${deviceInfo.internalStorage}"
            binding.availableInternalStorageTextView.text = "Available Internal Storage: ${deviceInfo.availableInternalStorage}"
            binding.externalStorageTextView.text = "External Storage: ${deviceInfo.externalStorage ?: "Unavailable"}"
            binding.availableExternalStorageTextView.text = "Available External Storage: ${deviceInfo.availableExternalStorage ?: "Unavailable"}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
