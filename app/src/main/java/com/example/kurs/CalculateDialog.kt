package com.example.kurs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kurs.databinding.DialogCalculateBinding
import com.example.kurs.retrofit.models.Data
import com.squareup.picasso.Picasso

open class CalculateDialog(
    private val data:Data
) : DialogFragment() {
    private lateinit var binding: DialogCalculateBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogCalculateBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.usemoney.text = data.CcyNm_UZ
        binding.usename.text=data.Ccy

        val substring = data.Ccy.substring(0, 2).lowercase()
        Picasso.get().load("https://flagcdn.com/w160/${substring}.png").into(binding.useimg)
        Picasso.get().load("https://flagcdn.com/w160/uz.png").into(binding.uzbimg)

        binding.uzbtxt.setText(data.Rate)

        binding.floatingActionButton.setOnClickListener{
            val c=data.Rate.toDouble()*binding.usetxt.text.toString().toDouble()
            binding.uzbtxt.setText(c.toString())
        }

        return binding.root
    }
}