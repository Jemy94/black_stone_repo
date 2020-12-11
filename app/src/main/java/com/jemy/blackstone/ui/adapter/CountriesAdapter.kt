package com.jemy.blackstone.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.jemy.blackstone.R
import com.jemy.blackstone.data.model.CountryItem
import com.jemy.blackstone.utils.extensions.inflate
import kotlinx.android.synthetic.main.item_countries.view.*
import kotlinx.android.synthetic.main.item_view_countries.view.*


/**
 * Authored by Ahmed Gamal on 10 Mar, 2020.
 * Contact: a.gamal@smartappco.com
 * by Smart App.
 */
class CountryAdapter constructor(
    context: Context,
    @LayoutRes id: Int,
    countryList: List<CountryItem>
) : ArrayAdapter<CountryItem?>(context, id, countryList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setupDropdownView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return setupView(position, convertView, parent)
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(position: Int, convertView: View?, parent: ViewGroup): View {

        val currentItem = getItem(position)
        val view = convertView ?: parent.inflate(R.layout.item_view_countries)

        view.currency.text = "(+${currentItem?.currency})"
        currentItem?.let { view.countryFlagImage.setImageResource(it.image) }

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setupDropdownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentItem = getItem(position)
        val view = convertView ?: parent.inflate(R.layout.item_countries)

        currentItem?.image?.let { view.countryDropFlagImage.setImageResource(it) }
        return view
    }
}