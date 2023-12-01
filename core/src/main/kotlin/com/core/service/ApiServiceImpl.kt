import org.springframework.stereotype.Service
import retrofit2.Retrofit

@Service
class ApiServiceImpl(val retrofit: Retrofit) : ApiService {
    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    override suspend fun getM2CurrencyVolume(): CurrencyData {
        return apiService.getM2CurrencyVolume()
    }

    override suspend fun getStockIndex(): CurrencyData {
        return apiService.getStockIndex()
    }
}
