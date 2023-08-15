Copy code
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Service
class ApiService(val retrofit: Retrofit) {
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