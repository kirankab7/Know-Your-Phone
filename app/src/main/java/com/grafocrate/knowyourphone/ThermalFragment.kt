package com.grafocrate.knowyourphone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.grafocrate.knowyourphone.databinding.FragmentThermalBinding

class ThermalFragment : Fragment() {

    private var _binding: FragmentThermalBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { ThermalViewModelFactory(requireContext()) }
    private val viewModel: ThermalViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThermalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.thermalInfo.observe(viewLifecycleOwner, Observer { thermalInfo ->
            binding.batteryTemperatureTextView.text = "Battery Temperature: ${thermalInfo.batteryTemperature}°C"
            binding.cpuTemperatureTextView.text = "CPU Temperature: ${thermalInfo.cpuTemperature}°C"
            binding.gpuTemperatureTextView.text = "GPU Temperature: ${thermalInfo.gpuTemperature}°C"
            binding.skinTemperatureTextView.text = "Skin Temperature: ${thermalInfo.skinTemperature}°C"
            binding.ambientTemperatureTextView.text = "Ambient Temperature: ${thermalInfo.ambientTemperature}°C"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
