package com.example.rxjavaexample.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaexample.databinding.CurrenciesItemBinding
import com.example.rxjavaexample.domain.ComplexData

class CountryAdapter: RecyclerView.Adapter<CountryHolder>() {

    private var complexData: ComplexData = ComplexData()

    @SuppressLint("NotifyDataSetChanged")
    fun setComplexData(newComplexData: ComplexData){
        complexData = newComplexData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(CurrenciesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return complexData.data.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        val currency = complexData.data[position]

        holder.binding.code.text = currency.code
        holder.binding.symbol.text = currency.symbol
        holder.binding.name.text = currency.name
    }
}

class CountryHolder(val binding: CurrenciesItemBinding): RecyclerView.ViewHolder(binding.root)