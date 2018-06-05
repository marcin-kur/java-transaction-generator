package classes.launcher;

        import org.springframework.context.annotation.Condition;
        import org.springframework.context.annotation.ConditionContext;
        import org.springframework.core.type.AnnotatedTypeMetadata;

        import java.util.Optional;

class YamlCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Optional<String> format = Optional.ofNullable(context.getEnvironment().getProperty("format"));
        return format.isPresent() && format.get().matches("yaml");
    }
}
