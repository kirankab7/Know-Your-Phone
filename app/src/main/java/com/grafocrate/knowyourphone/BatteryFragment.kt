package com.grafocrate.knowyourphone


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.grafocrate.knowyourphone.databinding.FragmentBatteryBinding

class BatteryFragment : Fragment() {

    private var _binding: FragmentBatteryBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { BatteryViewModelFactory(requireContext()) }
    private val viewModel: BatteryViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBatteryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.batteryInfo.observe(viewLifecycleOwner, Observer { result ->
            result.fold(
                onSuccess = { batteryInfo ->
                    binding.healthTextView.text = "Health: ${batteryInfo.health}"
                    binding.levelTextView.text = "Level: ${batteryInfo.level}%"
                    binding.powerSourceTextView.text = "Power Source: ${batteryInfo.powerSource}"
                    binding.statusTextView.text = "Status: ${batteryInfo.status}"
                    binding.technologyTextView.text = "Technology: ${batteryInfo.technology}"
                    binding.temperatureTextView.text = "Temperature: ${batteryInfo.temperature}°C"
                    binding.voltageTextView.text = "Voltage: ${batteryInfo.voltage}mV"
                },
                onFailure = {
                    binding.healthTextView.text = "Failed to load battery info"
                    binding.levelTextView.text = ""
                    binding.powerSourceTextView.text = ""
                    binding.statusTextView.text = ""
                    binding.technologyTextView.text = ""
                    binding.temperatureTextView.text = ""
                    binding.voltageTextView.text = ""
                }
            )
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
