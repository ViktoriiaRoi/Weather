package com.viktoriiaroi.weather.ui.weather

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.viktoriiaroi.weather.R
import com.viktoriiaroi.weather.databinding.FragmentWeatherBinding
import com.viktoriiaroi.weather.utils.DateUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()

    private var _binding: FragmentWeatherBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMenu()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherState.collect {
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: WeatherUIState) {
        binding.isLoading = state.isLoading
        binding.weather = state.weather

        state.weather?.let {
            binding.dateTv.text = DateUtils.timestampToDate(it.timestamp)
        }

        state.errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.onErrorShown()
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.weather_menu, menu)
                val searchItem = menu.findItem(R.id.search_item)
                setupSearch(searchItem)
            }

            override fun onMenuItemSelected(menuItem: MenuItem) = false
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupSearch(searchItem: MenuItem) {
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = requireContext().getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.fetchWeather(query)
                searchItem.collapseActionView()
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}