package com.evapps.event.screens.splash

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.evapps.event.R
import com.evapps.event.models.Language

class LanguageAdapter(val context: Context) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getItem(p0: Int): Any {
        return Language.languages[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return Language.languages.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate( R.layout.language_row, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        vh.label.text =  context.getResources().getString(Language.languages[position].name)

        vh.img.setBackgroundResource(Language.languages[position].icon)

        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.language) as TextView
        val img: ImageView = row?.findViewById(R.id.language_icon) as ImageView

    }
}