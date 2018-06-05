package classes.launcher;

import classes.input.InputParametersSupplier;
import classes.output.JmsProducer;
import classes.output.JmsQueueProducer;
import classes.output.JmsTopicProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import java.time.ZonedDateTime;

@Slf4j
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private InputParametersSupplier inputParametersSupplier;

    @Bean
    @Conditional(QueueCondition.class)
    public JmsProducer jsmQueueProducer() {
        return new JmsQueueProducer(inputParametersSupplier.get().getQueue());
    }

    @Bean
    @Conditional(TopicCondition.class)
    public JmsProducer jsmTopicProducer() {
        return new JmsTopicProducer(inputParametersSupplier.get().getTopic());
    }

    @Bean
    @Conditional(JsonCondition.class)
    public ObjectMapper jsonMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        addJavaTimeModule(objectMapper);
        return objectMapper;
    }

    @Bean
    @Conditional(XmlCondition.class)
    public ObjectMapper xmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        addJavaTimeModule(xmlMapper);
        return xmlMapper;
    }

    @Bean
    @Conditional(YamlCondition.class)
    public ObjectMapper yamlMapper() {
        YAMLMapper yamlMapper = new YAMLMapper();
        addJavaTimeModule(yamlMapper);
        return yamlMapper;
    }

    private void addJavaTimeModule(ObjectMapper objectMapper) {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        objectMapper.registerModule(javaTimeModule);
    }
}