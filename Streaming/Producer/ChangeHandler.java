package kafka.wikimedia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChangeHandler implements EventHandler {
    KafkaProducer<String,String> kafkaProducer;
    private static final Logger log = LoggerFactory.getLogger(ChangeHandler.class.getSimpleName());

    ChangeHandler(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }
    @Override
    public void onOpen() {}
    @Override
    public void onClosed() { kafkaProducer.close(); }
    @Override
    public void onMessage(String s, MessageEvent messageEvent) {
        log.info("URL1 Event: " + messageEvent.getData());
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(messageEvent.getData());

            String topic1Data = String.format("{\"id\":\"%s\", \"last_payment_d\":\"%s\" , \"last_payment_amnt\":\"%s\" , \"out_prncp\":\"%s\" , " +
                            "\"total_rec_prncp\":\"%s\", \"total_rec_int\":\"%s\", \"total_rec_late_fee\":\"%s\" }",
                    jsonNode.get("id").asText(), jsonNode.get("last_payment_d").asText(),
                    jsonNode.get("last_payment_amnt").asText(),
                    jsonNode.get("out_prncp").asText(),
                    jsonNode.get("total_rec_prncp").asText(),
                    jsonNode.get("total_rec_int").asText(),
                    jsonNode.get("total_rec_late_fee").asText());

            String topic2Data = String.format("{ \"id\":\"%s\" , \"loan_status\":\"%s\", \"delinq_amnt\":\"%s\"}",
                    jsonNode.get("id").asText(),
                    jsonNode.get("loan_status").asText(),
                    jsonNode.get("delinq_amnt").asText());

            String topic3Data = String.format("{\"id\":\"%s\" , \"last_credit_pull_d\":\"%s\", \"last_fico_range_high\":\"%s\"}" ,
                    jsonNode.get("id").asText(),
                    jsonNode.get("last_credit_pull_d").asText(),
                    jsonNode.get("last_fico_range_high").asText());

            kafkaProducer.send(new ProducerRecord<>("pymnt_recieved_by_borrower", topic1Data));
            kafkaProducer.send(new ProducerRecord<>("borrower_credit_score", topic3Data));
            kafkaProducer.send(new ProducerRecord<>("loan_status_streaming", topic2Data));

        } catch (JsonProcessingException e) {
            log.error("Parsing error in URL1 stream", e);
        }
    }
    @Override
    public void onComment(String s) {}
    @Override
    public void onError(Throwable t) { log.error("Error in URL1", t); }
}
