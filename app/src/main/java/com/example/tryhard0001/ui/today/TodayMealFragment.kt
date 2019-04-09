package com.example.tryhard0001.ui.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tryhard0001.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class TodayMealFragment : Fragment(), KodeinAware {

    companion object {
        fun newInstance() = TodayMealFragment()
    }

    private lateinit var viewModel: TodayMealViewModel
    override val kodein by closestKodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_meal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayMealViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
