package kafka.wikimedia;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class TopicConsumer implements Runnable {
    private final String topic;
    private final String tableName;
    public TopicConsumer(String topic, String tableName) {
        this.topic = topic;
        this.tableName = tableName;
    }
    @Override
    public void run() {
        try {
            // Kafka config
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_" + topic);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList(topic));

            // Snowflake connection
            String sfUrl = "jdbc:snowflake://ufqwvti-vl57671.snowflakecomputing.com";
            String sfUser = "Steam_user1";
            String sfPassword = "Loans_pwd_2025";
            String sfDatabase = "STREAMING_DB";
            String sfSchema = "RAW_SCHEMA";

            Connection conn = DriverManager.getConnection(
                    sfUrl + "/?db=" + sfDatabase + "&schema=" + sfSchema,
                    sfUser,
                    sfPassword
            );
            String sql = "INSERT INTO " + tableName + " (raw_data) SELECT PARSE_JSON(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    String jsonMessage = record.value();
                    stmt.setString(1, jsonMessage);
                    stmt.executeUpdate();
                    System.out.println("Inserted into " + tableName + ": " + jsonMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new TopicConsumer("pymnt_recieved_by_borrower", "PYMNT_RECIEVED_BY_BORROWER"));
        Thread t2 = new Thread(new TopicConsumer("loan_status_streaming", "LOAN_STATUS_STREAMING_RAW"));
        Thread t3 = new Thread(new TopicConsumer("borrower_credit_score", "BORROWER_CREDIT_SCORE_RAW"));

        t1.start();
        t2.start();
        t3.start();
    }
}
