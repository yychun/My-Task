package com.yychun1217.mychallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.yychun1217.mychallenge.databinding.FragmentDeliveryListBinding
import com.yychun1217.mychallenge.viewmodel.DeliveryListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryListFragment : Fragment() {
    @Inject
    lateinit var viewModel: DeliveryListViewModel
    private lateinit var binding: FragmentDeliveryListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryListBinding.inflate(inflater).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding.listDelivery) {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = DeliveryAdapter()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.appPage.collectLatest {
                (binding.listDelivery.adapter as? DeliveryAdapter)?.submitData(it)
            }
        }
    }
    }
}