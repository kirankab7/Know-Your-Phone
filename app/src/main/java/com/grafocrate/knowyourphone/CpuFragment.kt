package com.grafocrate.knowyourphone


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.grafocrate.knowyourphone.databinding.FragmentCpuBinding

class CpuFragment : Fragment() {

    private var _binding: FragmentCpuBinding? = null
    private val binding get() = _binding!!

    private val repository = CPURepository()
    private val viewModelFactory = CPUViewModelFactory(repository)
    private val viewModel: CPUViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCpuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cpuInfo.observe(viewLifecycleOwner, Observer { cpuInfo ->
            binding.modelTextView.text = "Model: ${cpuInfo.model}"
            binding.coresTextView.text = "Cores: ${cpuInfo.cores}"
            binding.architectureTextView.text = "Architecture: ${cpuInfo.architecture}"
            binding.clockSpeedTextView.text = "Clock Speed: ${cpuInfo.clockSpeed}"
            binding.numberOfCpusTextView.text = "Number of CPUs: ${cpuInfo.numberOfCpus}"
            binding.gpuTextView.text = "GPU: ${cpuInfo.gpu}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
