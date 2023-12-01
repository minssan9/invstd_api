import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(private val apiService: ApiService) {

    @GetMapping("/m2-currency-volume")
    suspend fun getM2CurrencyVolume(): CurrencyData {
        return apiService.getM2CurrencyVolume()
    }

    @GetMapping("/stock-index")
    suspend fun getStockIndex(): CurrencyData {
        return apiService.getStockIndex()
    }
}





