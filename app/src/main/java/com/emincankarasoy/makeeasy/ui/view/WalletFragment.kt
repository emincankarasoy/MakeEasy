package com.emincankarasoy.makeeasy.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.emincankarasoy.makeeasy.R
import com.emincankarasoy.makeeasy.databinding.FragmentWalletBinding
import com.emincankarasoy.makeeasy.ui.adapter.WalletPagerAdapter
import com.emincankarasoy.makeeasy.ui.view.wallet.WalletAccountFragment
import com.emincankarasoy.makeeasy.ui.view.wallet.WalletIncomingFragment
import com.emincankarasoy.makeeasy.ui.view.wallet.WalletOutcomingFragment
import com.google.android.material.tabs.TabLayoutMediator

class WalletFragment : Fragment() {

    private lateinit var binding: FragmentWalletBinding
    private lateinit var walletPagerAdapter: WalletPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        walletPagerAdapter = WalletPagerAdapter(
            arrayListOf(
                WalletAccountFragment(),
                WalletIncomingFragment(),
                WalletOutcomingFragment()
                ),this)

        binding.walletViewPager.adapter = walletPagerAdapter

        TabLayoutMediator(binding.walletTabLayout,binding.walletViewPager){ tab , position ->
            when(position){
                0 -> tab.text = "Account"
                1 -> tab.text = "Incoming"
                2 -> tab.text = "Outcoming"
            }
        }.attach()
    }
}