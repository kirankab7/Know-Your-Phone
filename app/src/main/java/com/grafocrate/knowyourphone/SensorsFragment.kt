package com.grafocrate.knowyourphone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.grafocrate.knowyourphone.databinding.FragmentSensorsBinding

class SensorsFragment : Fragment() {

    private var _binding: FragmentSensorsBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { SensorsViewModelFactory(requireContext()) }
    private val viewModel: SensorsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSensorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sensorsInfo.observe(viewLifecycleOwner, Observer { sensors ->
            binding.sensorsContainer.removeAllViews()
            sensors.forEach { sensor ->
                val sensorView = LayoutInflater.from(context).inflate(R.layout.item_sensor, binding.sensorsContainer, false)
                sensorView.findViewById<TextView>(R.id.sensorNameTextView).text = "Name: ${sensor.name}"
                sensorView.findViewById<TextView>(R.id.sensorTypeTextView).text = "Type: ${sensor.type}"
                sensorView.findViewById<TextView>(R.id.sensorVendorTextView).text = "Vendor: ${sensor.vendor}"
                sensorView.findViewById<TextView>(R.id.sensorVersionTextView).text = "Version: ${sensor.version}"
                sensorView.findViewById<TextView>(R.id.sensorMaxRangeTextView).text = "Max Range: ${sensor.maxRange}"
                sensorView.findViewById<TextView>(R.id.sensorResolutionTextView).text = "Resolution: ${sensor.resolution}"
                sensorView.findViewById<TextView>(R.id.sensorPowerTextView).text = "Power: ${sensor.power}"
                sensorView.findViewById<TextView>(R.id.sensorMinDelayTextView).text = "Min Delay: ${sensor.minDelay}"
                binding.sensorsContainer.addView(sensorView)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
