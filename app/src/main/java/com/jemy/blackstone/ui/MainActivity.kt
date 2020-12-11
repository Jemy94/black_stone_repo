package com.jemy.blackstone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jemy.blackstone.R
import com.jemy.blackstone.data.common.ResourceState
import com.jemy.blackstone.data.model.CountryItem
import com.jemy.blackstone.ui.adapter.CountryAdapter
import com.jemy.blackstone.utils.Constants
import com.jemy.blackstone.utils.extensions.toastLong
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private val isStringSameSize: (String, String) -> Boolean =
        { s1: String, s2: String -> s1.length == s2.length }
    private var x = 0
    private var y = 1
    private var selectedCountry = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstQuestion()
        getLatest()
        areStringAnagrams("heilo", "oktju", isStringSameSize)
        generateFibonacci1()
        Log.d("MainActivity", "nth Fibonacci is: ${generateFibonacci2(8)}")
        observeLatest()
        setupConvertClickListener()
    }

    private fun firstQuestion() {
        val x: Int = ((2.25 + 4.5 - 1.25) * 5).toInt()
        Log.d("MainActivity", "$x")
    }

    private fun areStringAnagrams(s1: String, s2: String, isEqual: (String, String) -> Boolean) {
        if (isEqual(s1, s2)) {
            val char1 = s1.toCharArray()
            val char2 = s2.toCharArray()
            Arrays.sort(char1)
            Arrays.sort(char2)
            val unrepeatedChar1 = char1.distinct()
            val unrepeatedChar2 = char2.distinct()
            val isCharEqual = unrepeatedChar1 == unrepeatedChar2
            if (isCharEqual) {
                Log.d("MainActivity", "Strings are anagram")
            } else {
                Log.d("MainActivity", "Strings are not anagram")
            }
        } else {
            Log.d("MainActivity", "Strings are not anagram")
        }
    }

    private fun getLatest() {
        viewModel.getLatest()
    }

    private fun generateFibonacci1() {
        val fibonacci = mutableListOf<Int>()
        for (i in 1..10) {
            fibonacci.add(x)
            val sum = x + y
            x = y
            y = sum
        }
        Log.d("MainActivity", fibonacci.toString())
        //print ninth Fibonacci
        Log.d("MainActivity", "nth Fibonacci is: ${fibonacci[8]}")
    }

    private fun generateFibonacci2(n: Int): Int {
        if (n <= 1) {
            return n
        }
        return generateFibonacci2(n - 1) + generateFibonacci2(n - 2)
    }

    private fun observeLatest() {
        viewModel.latest.observe(this, Observer { resource ->
            when (resource.state) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    resource.data?.let { latestResponse ->
                        val countriesList = mutableListOf<CountryItem>()
                        countriesList.add(CountryItem(latestResponse.rates.eGP, R.drawable.eg))
                        countriesList.add(CountryItem(latestResponse.rates.sAR, R.drawable.sa))
                        countriesList.add(CountryItem(latestResponse.rates.aED, R.drawable.ae))
                        countriesList.add(CountryItem(latestResponse.rates.uSD, R.drawable.us))
                        fillCountriesSpinner(countriesList)
                    }
                }
                ResourceState.ERROR -> {
                    when (resource.message) {
                        Constants.Error.GENERAL -> toastLong(getString(R.string.general_error))
                        else -> toastLong(getString(R.string.general_error))
                    }
                }
            }
        })
    }

    private fun fillCountriesSpinner(countriesList: List<CountryItem> = mutableListOf()) {
        val displayMetrics = DisplayMetrics()
        this.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        currencySpinner.dropDownWidth = width - 100
        val countriesAdapter = CountryAdapter(this, R.layout.item_countries, countriesList)
        currencySpinner.adapter = countriesAdapter
        currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                if (countriesList.isNotEmpty()) {
                    selectedCountry = countriesList[i].currency
                }
            }
        }
    }

    private fun setupConvertClickListener() {
        convertButton.setOnClickListener {
            if (selectedCountry == 0.0) {
                toastLong("Please select currency to convert to it")
            } else {
                val amountStr = amountEditText.text.toString()
                if (amountStr.isNotBlank()) {
                    val amount = amountStr.toDouble()
                    convertedAmount.text = "${selectedCountry * amount}"
                } else {
                    toastLong("Please enter amount to convert")
                }


            }
        }
    }

}