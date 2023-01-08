package com.example.emapp.fragment

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.emapp.R
import com.example.emapp.view.AccountUser
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var languageList = ArrayList<String>()
private lateinit var locale: Locale

class SettingsFragment : BaseFragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        languageList = ArrayList(listOf("...","English","Русский"))
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, languageList)
        spinerLanguage.adapter = adapter
        spinerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 ->{

                    }
                    1 -> setLocale("en")
                    2 -> setLocale("ru")
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }
        }
    }

    fun setLocale(languageName: String){
        locale = Locale(languageName)
        val res = resources
        val dm = res.displayMetrics
        val config = res.configuration
        config.locale = locale
        res.updateConfiguration(config,dm)
        languageList.removeAll(languageList.toSet())
        (this.requireActivity() as AccountUser).refreshActivity()

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        newConfig.setLocale(locale)
        super.onConfigurationChanged(newConfig)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}