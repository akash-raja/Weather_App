package com.example.weatherapp.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.fragment_error.*

class ErrorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        retry_button.setOnClickListener {
            findNavController(this).navigate(R.id.action_error_fragment_to_weather_fragment)
        }
    }
}
