import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class Parser {
    fun run(html: String): Int {
        val doc: Document = Jsoup.parse(html)
        val drugs: List<Element> = doc.select(".product").toList()
        val prices: List<Int> = drugs.mapNotNull {
            it.select(".product-price > span > span[data-qa]").text().toIntOrNull()
        }
        val minPrice = prices.toIntArray().min()
        return minPrice
    }
}
