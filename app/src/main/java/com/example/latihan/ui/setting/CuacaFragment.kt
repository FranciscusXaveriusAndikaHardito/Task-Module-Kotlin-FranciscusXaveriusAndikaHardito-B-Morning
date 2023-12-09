package com.example.latihan.ui.setting

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.latihan.databinding.FragmentSettingBinding
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CuacaFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding
    val CITY: String = "republic of indonesia,id"
    val API: String = "c669634a8bccec847aedd2bab165a270" // Use API key

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentSettingBinding.inflate(layoutInflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherTask().execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
                binding?.loader?.visibility = View.VISIBLE
            binding?.mainContainer?.visibility = View.GONE
            binding?.errorText?.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readText(Charsets.UTF_8)
            }catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

                val updatedAt:Long = jsonObj.getLong("id")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Calendar.getInstance().time)
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")

                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                /* Populating extracted data into our views */
                binding?.address?.text = address
                binding?.updatedAt?.text =  updatedAtText
                binding?.status?.text = weatherDescription.capitalize()
                binding?.temp?.text = temp
                binding?.tempMin?.text = tempMin
                binding?.tempMax?.text = tempMax
                binding?.sunrise?.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunrise*1000)
                )
                binding?.sunset?.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunset*1000)
                )
                binding?.wind?.text = windSpeed
                binding?.pressure?.text = pressure
                binding?.humidity?.text = humidity

                /* Views populated, Hiding the loader, Showing the main design */
                binding?.loader?.visibility = View.GONE
                binding?.mainContainer?.visibility = View.VISIBLE

            } catch (e: Exception) {
                binding?.loader?.visibility = View.GONE
                binding?.errorText?.visibility = View.VISIBLE
            }

        }
    }

}