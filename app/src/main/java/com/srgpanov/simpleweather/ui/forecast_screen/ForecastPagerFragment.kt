package com.srgpanov.simpleweather.ui.forecast_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.srgpanov.simpleweather.MainActivity
import com.srgpanov.simpleweather.databinding.ForecastPagerFragmentBinding
import com.srgpanov.simpleweather.other.addSystemWindowInsetToMargin
import com.srgpanov.simpleweather.ui.ShareViewModel

class ForecastPagerFragment : Fragment() {
    private lateinit var viewModel: ForecastPagerViewModel
    private lateinit var shareViewModel: ShareViewModel
    private lateinit var dateAdapter: CalendarAdapter
    private lateinit var forecastAdapter: ForecastPagerAdapter
    private var _binding: ForecastPagerFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ForecastPagerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ForecastPagerFragmentBinding.inflate(layoutInflater, container, false)
        prepareViews()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastPagerViewModel::class.java)
        shareViewModel=ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
         shareViewModel.request.observe(viewLifecycleOwner, Observer {
             forecastAdapter.forecasts=it.forecasts.toMutableList()
         })

    }

    private fun prepareViews() {
        setupToolbar()
        setupInsets()
        setupCalendar()

    }
    private fun setupInsets() {
        binding.appbarLayout.addSystemWindowInsetToMargin(top = true)
       // binding.scrollView.addSystemWindowInsetToPadding(bottom = true)
    }

    private fun setupCalendar() {
        dateAdapter= CalendarAdapter()
        binding.calendar.adapter=dateAdapter
        forecastAdapter=ForecastPagerAdapter()
        binding.viewPager.adapter=forecastAdapter
        binding.viewPager.orientation=ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun setupToolbar() {
        if (activity is MainActivity) {
            val mainActivity = (activity as MainActivity)
            mainActivity.setSupportActionBar(binding.toolbar)

        }
    }
}
