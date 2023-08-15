import retrofit2.http.GET

interface ApiService {
    @GET("m2_currency_volume")
    suspend fun getM2CurrencyVolume(): CurrencyData

    @GET("stock_index")
    suspend fun getStockIndex(): CurrencyData
}