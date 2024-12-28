package com.example.rxjavaexample.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaexample.databinding.CurrenciesItemBinding
import com.example.rxjavaexample.domain.ComplexData
import com.example.rxjavaexample.domain.Data

class CountryAdapter: RecyclerView.Adapter<CountryHolder>() {

    private var currencyes: List<Data> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setComplexData(newCurrencyes: List<Data>){
        currencyes = newCurrencyes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(CurrenciesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        Log.d("+++", "getItemCount: ${currencyes.size}")
        return currencyes.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        val currency = currencyes[position]

        holder.binding.code.text = currency.code
        holder.binding.symbol.text = currency.symbol
        holder.binding.name.text = currency.name

        Log.d("+++", "onBindViewHolder: ${currency}")
    }
}

class CountryHolder(val binding: CurrenciesItemBinding): RecyclerView.ViewHolder(binding.root)