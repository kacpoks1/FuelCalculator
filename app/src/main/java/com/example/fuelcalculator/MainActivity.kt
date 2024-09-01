package com.example.fuelcalculator


import android.content.Context
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

        // Pobierz SharedPreferences
        val sharedPref = getSharedPreferences("Car", Context.MODE_PRIVATE)

        // Załaduj zapisane wartości i wyświetl je na starcie
        val totalKilometers = sharedPref.getInt("km", 0)
        val totalValue = sharedPref.getInt("money", 0)
        val totalFuel = sharedPref.getInt("liters", 0)
        val totalTrip = sharedPref.getInt("trip", 0)

        // Oblicz spalanie na 100 km
        if (totalKilometers > 0) {
            val fuelConsumption = (totalFuel.toDouble() / totalKilometers.toDouble()) * 100
            val roundedFuelConsumption = "%.2f".format(fuelConsumption)
            binding.spalanieDane.setText("Spalanie: $roundedFuelConsumption l/100km")
        } else {
            binding.spalanieDane.setText("Enter valid kilometers")
        }
        binding.kosztCalkowityDane.setText("$totalValue zł")
        binding.odlegloscDane.setText("$totalKilometers km")



        // Reszta kodu, np. obsługa przycisków itd.
        binding.confirm.setOnClickListener {
            val prevKilometers = sharedPref.getInt("km", 0)
            val prevValue = sharedPref.getInt("money", 0)
            val prevFuel = sharedPref.getInt("liters", 0)

            val newKilometers = binding.kilomiters.text.toString().toInt()
            val newValue = binding.value.text.toString().toInt()
            val newFuel = binding.fuel.text.toString().toInt()
            val date = binding.date.text.toString()

            val totalKilometers = prevKilometers + newKilometers
            val totalValue = prevValue + newValue
            val totalFuel = prevFuel + newFuel
            val totalTrip =  prevKilometers + newKilometers



            val editor = sharedPref.edit()
            editor.apply {
                putInt("km", totalKilometers)
                putInt("money", totalValue)
                putInt("liters", totalFuel)
                putString("day", date)
                apply()
            }


            binding.kosztCalkowityDane.setText("$totalValue zł")
            binding.odlegloscDane.setText("$totalKilometers km")

            if (totalKilometers > 0) {
                val fuelConsumption = (totalFuel.toDouble() / totalKilometers.toDouble()) * 100
                val roundedFuelConsumption = "%.2f".format(fuelConsumption)
                binding.spalanieDane.setText("Spalanie: $roundedFuelConsumption l/100km")
            } else {
                binding.spalanieDane.setText("Enter valid kilometers")
            }
        }







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

