import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val baseUrl = "https://api.example.com/" // Replace with the actual API base URL

    private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun getM2CurrencyVolume(): Double {
        val response = apiService.getM2CurrencyVolume()
        return response.m2CurrencyVolume
    }

    suspend fun getStockIndex(): Double {
        val response = apiService.getStockIndex()
        return response.stockIndex
    }
}