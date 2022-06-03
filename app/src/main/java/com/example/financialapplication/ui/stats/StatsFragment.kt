package com.example.financialapplication.ui.stats

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.financialapplication.R
import com.example.financialapplication.data.extensions.empty
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPieChart()
        addDataToPieChart(requireArguments().getSerializable(ARG_VALUES_KEY) as HashMap<String, Int>)
    }

    private fun setupPieChart() {
        statsPieChart.apply {
            setUsePercentValues(true)
            description.text = ""
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            setDrawEntryLabels(false)
            setExtraOffsets(20f, 0f, 20f, 20f)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
        }
    }


    private fun addDataToPieChart(values: HashMap<String, Int>) {
        statsPieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()

        values.forEach {
            Log.i("asd", values.toString())
            dataEntries.add(PieEntry(it.value.toFloat(), it.key))
        }

        val colors: ArrayList<Int> = ArrayList()
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor1))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor2))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor3))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor4))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor5))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor6))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor7))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor8))
        colors.add(ContextCompat.getColor(requireContext(), R.color.pieChartColor9))

        val dataSet = PieDataSet(dataEntries, String.empty)
        val data = PieData(dataSet)

        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        statsPieChart.data = data
        data.setValueTextSize(12f)
        statsPieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        statsPieChart.animateY(1400, Easing.EaseInOutQuad)

        statsPieChart.holeRadius = 58f
        statsPieChart.transparentCircleRadius = 61f
        statsPieChart.isDrawHoleEnabled = true
        statsPieChart.setHoleColor(Color.WHITE)

        statsPieChart.setDrawCenterText(true)
        statsPieChart.centerText = requireContext().getString(R.string.stats_pie_chart_title)

        statsPieChart.invalidate()
    }

    companion object {
        private const val ARG_VALUES_KEY = "ARG_VALUES_KEY"

        @JvmStatic
        fun newInstance(values: HashMap<String, Int>) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_VALUES_KEY, values)
                }
            }
    }
}