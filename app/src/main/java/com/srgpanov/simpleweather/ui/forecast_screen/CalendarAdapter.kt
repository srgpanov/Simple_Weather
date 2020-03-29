package com.srgpanov.simpleweather.ui.forecast_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.simpleweather.databinding.CalendarDayItemBinding
import com.srgpanov.simpleweather.other.MyClickListener
import com.srgpanov.simpleweather.other.logD
import java.util.*

class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.DateViewHolder>() {
    private lateinit var  dates :MutableList<Date>
    var listener: MyClickListener? = null
    init {
        this.dates=mutableListOf<Date>()
        repeat(10,{

            dates.add(Date(System.currentTimeMillis()))
        })
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DateViewHolder(CalendarDayItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dates[position])
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    inner class DateViewHolder(val binding: CalendarDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Date){
            logD(date.toString())
            binding.day.text=date.day.toString()
            binding.numbers.text=date.date.toString()
            binding.linearLayout.setOnClickListener { view: View? ->
                listener?.onClick(view,adapterPosition)
            }
        }
    }
}