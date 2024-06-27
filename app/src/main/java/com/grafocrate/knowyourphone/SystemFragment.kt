package com.grafocrate.knowyourphone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.grafocrate.knowyourphone.databinding.FragmentSystemBinding

class SystemFragment : Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy { SystemViewModelFactory(requireContext()) }
    private val viewModel: SystemViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.systemInfo.observe(viewLifecycleOwner, Observer { systemInfo ->
            binding.androidVersionTextView.text = "Android Version: ${systemInfo.androidVersion}"
            binding.apiLevelTextView.text = "API Level: ${systemInfo.apiLevel}"
            binding.securityPatchLevelTextView.text = "Security Patch Level: ${systemInfo.securityPatchLevel}"
            binding.bootloaderTextView.text = "Bootloader: ${systemInfo.bootloader}"
            binding.buildIdTextView.text = "Build ID: ${systemInfo.buildId}"
            binding.javaVmTextView.text = "Java VM: ${systemInfo.javaVM}"
            binding.openGlesTextView.text = "OpenGLES: ${systemInfo.openGLES}"
            binding.kernelArchitectureTextView.text = "Kernel Architecture: ${systemInfo.kernelArchitecture}"
            binding.kernelVersionTextView.text = "Kernel Version: ${systemInfo.kernelVersion}"
            binding.rootAccessTextView.text = "Root Access: ${systemInfo.rootAccess}"
            binding.googlePlayServicesTextView.text = "Google Play Services: ${systemInfo.googlePlayServices}"
            binding.systemUptimeTextView.text = "System Uptime: ${systemInfo.systemUptime}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
