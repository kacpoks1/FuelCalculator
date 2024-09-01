package com.example.fuelcalculator


import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.fuelcalculator.databinding.ActivityMainBinding
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()








        val editTextDate = findViewById<EditText>(R.id.date)

        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().time)

        editTextDate.setText(currentDate)

        val graphView: GraphView = findViewById(R.id.idGraphView)

        // Dodawanie danych do GraphView
        val series = LineGraphSeries(
            arrayOf(
                DataPoint(0.0, 20.0),
                DataPoint(1.0, 3.0),
                DataPoint(2.0, 4.0),
                DataPoint(3.0, 10.0),
                DataPoint(4.0, 6.0),
                DataPoint(5.0, 0.0),
                DataPoint(6.0, 6.0),
                DataPoint(7.0, 1.0),
                DataPoint(8.0, 2.0)
            )
        )

        // Ustawianie tytułu
        graphView.title = "Spalanie samochodu"


        // Ustawianie rozmiaru tekstu tytułu
        graphView.titleTextSize = 24f

        // Dodawanie serii danych do GraphView
        graphView.addSeries(series)
    }




}

