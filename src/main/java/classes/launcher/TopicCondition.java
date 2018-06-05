package classes.launcher;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

class TopicCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Optional<String> topic = Optional.ofNullable(context.getEnvironment().getProperty("topic"));
        return topic.isPresent();
    }
}