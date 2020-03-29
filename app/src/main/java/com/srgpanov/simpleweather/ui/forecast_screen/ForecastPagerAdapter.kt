package com.srgpanov.simpleweather.ui.forecast_screen

import Forecasts
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.simpleweather.R
import com.srgpanov.simpleweather.databinding.ForecastPagerItemBinding
import com.srgpanov.simpleweather.other.*
import java.util.*

class ForecastPagerAdapter() : RecyclerView.Adapter<ForecastPagerAdapter.ForecastHolder>() {
    var forecasts = mutableListOf<Forecasts>()
        set(value) {
            field.clear()
            field.addAll(value)
            logD(value.toString())
            notifyDataSetChanged()
        }
    var listener: MyClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastHolder(ForecastPagerItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        holder.bind(forecasts[position])
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    inner class ForecastHolder(val binding: ForecastPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(forecasts: Forecasts) {
            binding.cloudnessTempMorningTv.text = formatTemp(forecasts.parts.morning.temp_avg)
            binding.cloudnessTempDayTv.text = formatTemp(forecasts.parts.day.temp_avg)
            binding.cloudnessTempEveningTv.text = formatTemp(forecasts.parts.evening.temp_avg)
            binding.cloudnessTempNightTv.text = formatTemp(forecasts.parts.night.temp_avg)
//
            binding.cloudnessFeelsMorningTv.text = formatTemp(forecasts.parts.morning.feels_like)
            binding.cloudnessFeelsDayTv.text = formatTemp(forecasts.parts.day.feels_like)
            binding.cloudnessFeelsEveningTv.text = formatTemp(forecasts.parts.evening.feels_like)
            binding.cloudnessFeelsNightTv.text = formatTemp(forecasts.parts.night.feels_like)
            //
            val morningSpeed =
                "${forecasts.parts.morning.wind_speed.toInt()} ${context.getString(R.string.m_in_s)}"
            binding.windSpeedMorningTv.text = morningSpeed
            val daySpeed =
                "${forecasts.parts.day.wind_speed.toInt()} ${context.getString(R.string.m_in_s)}"
            binding.windSpeedDayTv.text = daySpeed
            val eveningSpeed =
                "${forecasts.parts.evening.wind_speed.toInt()} ${context.getString(R.string.m_in_s)}"
            binding.windSpeedEveningTv.text = eveningSpeed
            val nightSpeed =
                "${forecasts.parts.night.wind_speed.toInt()} ${context.getString(R.string.m_in_s)}"
            binding.windSpeedNightTv.text = nightSpeed
//
            val morningGustSpeed =
                "${context.getString(R.string.to)} ${forecasts.parts.morning.wind_gust.toInt()} ${context.getString(
                    R.string.m_in_s
                )}"
            binding.windSpeedUpMorningTv.text = morningGustSpeed
            val dayGustSpeed =
                "${context.getString(R.string.to)} ${forecasts.parts.day.wind_gust.toInt()} ${context.getString(
                    R.string.m_in_s
                )}"
            binding.windSpeedUpDayTv.text = dayGustSpeed
            val eveningGustSpeed =
                "${context.getString(R.string.to)} ${forecasts.parts.evening.wind_gust.toInt()} ${context.getString(
                    R.string.m_in_s
                )}"
            binding.windSpeedUpEveningTv.text = eveningGustSpeed
            val nightGustSpeed =
                "${context.getString(R.string.to)} ${forecasts.parts.night.wind_gust.toInt()} ${context.getString(
                    R.string.m_in_s
                )}"
            binding.windSpeedUpNightTv.text = nightGustSpeed
//
            binding.windDirectionMorningTv.text =
                forecasts.parts.morning.wind_dir.toUpperCase(Locale.getDefault())
            binding.windDirectionDayTv.text =
                forecasts.parts.day.wind_dir.toUpperCase(Locale.getDefault())
            binding.windDirectionEveningTv.text =
                forecasts.parts.evening.wind_dir.toUpperCase(Locale.getDefault())
            binding.windDirectionNightTv.text =
                forecasts.parts.night.wind_dir.toUpperCase(Locale.getDefault())
//
            binding.windDirectionMorningIv.setImageResource(
                getWindDirectionIcon(
                    forecasts.parts.morning.wind_dir
                )
            )
            binding.windDirectionDayIv.setImageResource(
                getWindDirectionIcon(
                    forecasts.parts.day.wind_dir
                )
            )
            binding.windDirectionEveningIv.setImageResource(
                getWindDirectionIcon(
                    forecasts.parts.evening.wind_dir
                )
            )
            binding.windDirectionNightIv.setImageResource(
                getWindDirectionIcon(
                    forecasts.parts.night.wind_dir
                )
            )
            val morningHumidity = "${forecasts.parts.morning.humidity}%"
            binding.humidityPercentMorningTv.text = morningHumidity
            val dayHumidity = "${forecasts.parts.morning.humidity}%"
            binding.humidityPercentDayTv.text = dayHumidity
            val eveningHumidity = "${forecasts.parts.evening.humidity}%"
            binding.humidityPercentEveningTv.text = eveningHumidity
            val nightHumidity = "${forecasts.parts.night.humidity}%"
            binding.humidityPercentNightTv.text = nightHumidity
//
            binding.pressureValueMorningTv.text = forecasts.parts.morning.pressure_pa.toString()
            binding.pressureValueDayTv.text = forecasts.parts.day.pressure_pa.toString()
            binding.pressureValueEveningTv.text = forecasts.parts.evening.pressure_pa.toString()
            binding.pressureValueNightTv.text = forecasts.parts.night.pressure_pa.toString()
            //
            binding.sunriseTimeTv.text = forecasts.sunrise
            binding.sunsetTimeTv.text = forecasts.sunset
            try {
                val daylightHours =
                    forecasts.sunset.split(":")[0].toInt() - forecasts.sunrise.split(":")[0].toInt()
                val daylightMinutes =
                    forecasts.sunset.split(":")[1].toInt() - forecasts.sunrise.split(":")[1].toInt()
                val daylight = when (daylightMinutes < 0) {
                    true -> {
                        "${daylightHours - 1} h ${60 + daylightMinutes} min"
                    }
                    false -> "$daylightHours h $daylightMinutes min"
                }
                binding.solarDayValueTv.text = daylight
            } catch (e: Exception) {
                logE(e.message.toString())
            }
//            binding.mock.setOnClickListener { view: View? ->
//                listener?.onClick(view,adapterPosition)
//            }
        }
    }
}