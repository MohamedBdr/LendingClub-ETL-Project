package kafka.wikimedia;
import com.launchdarkly.eventsource.EventSource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class producer {
    public static void main(String[] args) throws InterruptedException {

        String bootstrapServer = "localhost:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // Safe producer configs
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        // High throughput configs
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024));
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        // Create Kafka Producer (shared)
        KafkaProducer<String, String> kproducer = new KafkaProducer<>(properties);

        // URL 1 handler (for topic1 + topic3)
        ChangeHandler handler1 = new ChangeHandler(kproducer);

        String Url1 = "http://44.201.87.53:5000/stream";

        EventSource eventSource1 = new EventSource.Builder(handler1, URI.create(Url1)).build();

        // Start both in background
        eventSource1.start();
    }
}
