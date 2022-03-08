package com.emincankarasoy.makeeasy.ui.view.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.databinding.FragmentWalletAccountBinding

class WalletAccountFragment : Fragment() {

    private lateinit var binding: FragmentWalletAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet_account,container,false)
        return binding.root
    }


}