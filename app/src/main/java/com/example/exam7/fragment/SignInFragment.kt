package com.example.exam7.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exam7.R
import com.example.exam7.adapter.ButtonAdapter
import com.example.exam7.databinding.FragmentSignInBinding
import com.example.exam7.viewmodel.SignInViewModel
import com.google.android.material.snackbar.Snackbar

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    FragmentSignInBinding::inflate,
    SignInViewModel::class.java
) {
    private var result = ""
    private var result1 = ""

    private lateinit var adapter: ButtonAdapter

    override fun start() {
        init()
    }

    private fun init() {
        initRecycler()
    }

    private fun initRecycler() {
        adapter = ButtonAdapter()
        binding.rvButtons.adapter = adapter.apply {
            onButtonClick = {
                if (result.length < 4) {
                    result += it
                    updateCircle(result)
                    if (result.length == 4) {
                        binding.tvEnter.text = getString(R.string.repeat)
                        updateCircle(result1)
                    }
                } else if (result1.length < 4) {
                    result1 += it
                    updateCircle(result1)
                    if (result1.length == 4 ) {
                        if(result == result1) {
                            Snackbar.make(binding.root, result, Snackbar.LENGTH_LONG).show()
                            result = ""
                            result1 = ""
                            binding.tvEnter.text = getString(R.string.)
                            updateCircle(result)
                        }else{
                            Snackbar.make(binding.root,getString(R.string.invalid),Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
            onBackClick = {
                if (result1.isBlank()) {
                    result = result.dropLast(1)
                    updateCircle(result)
                }else{
                    result1 = result1.dropLast(1)
                    updateCircle(result1)
                }
            }
        }
        binding.rvButtons.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun updateCircle(result: String) {
        binding.circleView.forEachIndexed { index, view ->
            if (index <= result.length - 1)
                view.setBackgroundResource(R.drawable.circle_green)
            else
                view.setBackgroundResource(R.drawable.circle_white)
        }
    }
}