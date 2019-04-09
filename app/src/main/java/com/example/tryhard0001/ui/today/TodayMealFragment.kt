package com.example.tryhard0001.ui.today

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.tryhard0001.R
import com.example.tryhard0001.data.db.model.Meal
import com.example.tryhard0001.ui.base.ScopedFragment
import com.example.tryhard0001.ui.editmeal.EditMealActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.today_meal_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.threeten.bp.OffsetDateTime

class TodayMealFragment : ScopedFragment(), KodeinAware {

    companion object {
        fun newInstance() = TodayMealFragment()
    }

    private val newMealActivityResultCode = 1
    private lateinit var viewModel: TodayMealViewModel
    override val kodein by closestKodein()
    private val viewModelFactory: TodayMealViewModelFactory by instance()
    lateinit var groupAdapter: GroupAdapter<ViewHolder>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupAdapter = GroupAdapter<ViewHolder>()

        return inflater.inflate(R.layout.today_meal_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayMealViewModel::class.java)
        val fabButton = activity!!.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            val intent = Intent(context, EditMealActivity::class.java )
            startActivityForResult(intent, newMealActivityResultCode)
        }

        BindUI()
    }

    private fun BindUI() = launch(Dispatchers.Main) {

        val todayMealEntries = viewModel.todayMealEntries.await()
        todayMealEntries.observe(this@TodayMealFragment, Observer {
            if (it == null) return@Observer
            initRecyclerView(it.toTodayMealItems())
            Log.d("cec", it.toString())

        })
    }

    private fun initRecyclerView(items: List<TodayMealItem>) {
        groupAdapter = GroupAdapter<ViewHolder>().apply { addAll(items) }

        today_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@TodayMealFragment.context)
            adapter = groupAdapter
        }
    }

    private fun List<Meal>.toTodayMealItems() : List<TodayMealItem> {
        return this.map {
            TodayMealItem(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        when (resultCode) {
            EditMealActivity.RESULT_CANCELED_WRONG_NAME ->
                Toast.makeText(activity!!.applicationContext,
                    "Enter name",
                    Toast.LENGTH_LONG
                ).show()
            EditMealActivity.RESULT_CANCELED_WRONG_CALORIES ->
                Toast.makeText(activity!!.applicationContext,
                    "Enter calories count",
                    Toast.LENGTH_LONG
                ).show()
            else -> {
                intentData?.let { data ->
                    val date = OffsetDateTime.now()
                    val meal = Meal(
                        0,
                        data.getStringExtra(EditMealActivity.NAME_REPLY),
                        data.getStringExtra(EditMealActivity.CALORIES_REPLY).toInt(),
                        null,                                              //TODO сделать получение списка ингредиентов
                        data.getStringExtra(EditMealActivity.GRAMS_REPLY).toIntOrNull(),
                        data.getStringExtra(EditMealActivity.CALORIES_PER_HUNDRED_REPLY).toIntOrNull(),
                        data.getStringExtra(EditMealActivity.UNIQUE_REPLY)!!.toBoolean(),
                        date
                    )
                    launch(Dispatchers.IO) {
                        viewModel.insertMeal(meal)
                    }
                }
            }
        }
    }

}
