package com.example.currencyapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val API_KEY = "7fb0668426595cfae05e89c781a80e49"
    private lateinit var sourceAmount: EditText
    private lateinit var destinationAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var destinationCurrency: Spinner
    private lateinit var apiService: CurrencyApiService
    private val currencySymbols = mutableListOf<String>()
    private val exchangeRates = mutableMapOf<String, Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo các view
        sourceAmount = findViewById(R.id.sourceAmount)
        destinationAmount = findViewById(R.id.destinationAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        destinationCurrency = findViewById(R.id.destinationCurrency)

        // Thiết lập Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(CurrencyApiService::class.java)

        // Gọi API để lấy danh sách tiền tệ và cập nhật vào Spinner
        loadCurrencySymbols()
    }

    private fun loadCurrencySymbols() {
        apiService.getCurrencySymbols(API_KEY).enqueue(object : Callback<CurrencySymbolsResponse> {
            override fun onResponse(call: Call<CurrencySymbolsResponse>, response: Response<CurrencySymbolsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.symbols?.let {
                        currencySymbols.addAll(it.keys)
                        setupSpinners()
                    }
                }
            }

            override fun onFailure(call: Call<CurrencySymbolsResponse>, t: Throwable) {
                // Xử lý lỗi
            }
        })
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencySymbols)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrency.adapter = adapter
        destinationCurrency.adapter = adapter

        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Gọi hàm calculateConversion khi có thay đổi trong sourceAmount
                calculateConversion()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Đặt listener để cập nhật kết quả khi thay đổi tiền tệ
        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                loadExchangeRates()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        destinationCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                calculateConversion()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun loadExchangeRates() {
        val baseCurrency = sourceCurrency.selectedItem.toString()
        apiService.getExchangeRates(API_KEY, baseCurrency).enqueue(object : Callback<ExchangeRatesResponse> {
            override fun onResponse(call: Call<ExchangeRatesResponse>, response: Response<ExchangeRatesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.rates?.let {
                        exchangeRates.clear()
                        exchangeRates.putAll(it)
                        calculateConversion()
                    }
                }
            }

            override fun onFailure(call: Call<ExchangeRatesResponse>, t: Throwable) {
                // Xử lý lỗi
            }
        })
    }

    private fun calculateConversion() {
        val amount = sourceAmount.text.toString().toDoubleOrNull() ?: return
        val targetCurrency = destinationCurrency.selectedItem.toString()
        val rate = exchangeRates[targetCurrency] ?: return
        val convertedAmount = amount * rate
        destinationAmount.setText(String.format("%.2f", convertedAmount))
    }
}



