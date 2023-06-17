import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.*

class Consumer {
    fun run(): String {
        val props = Properties()
        props.load(this.javaClass.getResourceAsStream("consumer.properties"))
        val consumer: KafkaConsumer<String, String> = KafkaConsumer(props)
        consumer.subscribe(listOf("html"))
        var records: ConsumerRecords<String, String> = consumer.poll(Duration.ofSeconds(100))
        while (records.isEmpty) {
            Thread.sleep(Duration.ofSeconds(1L).toMillis())
            records = consumer.poll(Duration.ofSeconds(1))
        }
        val result = records.first().value()
        consumer.close()
        return result
    }
}
