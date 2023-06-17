import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties

class Producer {
    private val kafkaProducer: KafkaProducer<String, String>
    init {
        val props = Properties()
        props.load(this.javaClass.getResourceAsStream("producer.properties"))
        kafkaProducer = KafkaProducer(props)
    }

    fun send(price: Int, siteName: String) {
        val producerRecord: ProducerRecord<String, String> = ProducerRecord("result", "result-$siteName", "$siteName: $price")
        kafkaProducer.send(producerRecord)
    }

    fun close() {
        kafkaProducer.close()
    }
}
