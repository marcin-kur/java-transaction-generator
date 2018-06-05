package classes.launcher;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

class QueueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Optional<String> queue = Optional.ofNullable(context.getEnvironment().getProperty("queue"));
        return queue.isPresent();
    }
}
