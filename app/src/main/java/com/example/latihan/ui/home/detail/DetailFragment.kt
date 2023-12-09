package com.example.latihan.ui.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.latihan.R
import com.example.latihan.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding
    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jadwalsArgs = navArgs.jadwal
        binding?.tvHari?.text = jadwalsArgs.hari
        binding?.tvDate?.text = jadwalsArgs.waktu
        binding?.tvMapel?.text = jadwalsArgs.mapel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}