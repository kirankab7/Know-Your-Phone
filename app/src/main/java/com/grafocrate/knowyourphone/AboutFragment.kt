package com.grafocrate.knowyourphone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grafocrate.knowyourphone.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set application name, developer name, and company name
        binding.appNameTextView.text = "Know Your Phone" // Replace with your application name
        binding.developerNameTextView.text = "Developer - Kiran A Bendigeri" // Replace with developer name
        binding.companyNameTextView.text = "Grafocrate" // Replace with company name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

