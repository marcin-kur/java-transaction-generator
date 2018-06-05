package classes.launcher;

import joptsimple.OptionParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.JOptCommandLinePropertySource;

import java.util.Arrays;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Application started...");
        log.info("Args: " + Arrays.toString(args));

        TransactionGeneratorStarter starter = getApplicationContext(args).getBean(TransactionGeneratorStarter.class);
        starter.start();

        log.info("Application ended...");
    }

    private static ApplicationContext getApplicationContext(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        setContextPropertySource(ctx, args);
        ctx.scan("classes");
        ctx.refresh();
        return ctx;
    }

    private static void setContextPropertySource(AnnotationConfigApplicationContext ctx, String[] args) {
        JOptCommandLinePropertySource propertySource = new JOptCommandLinePropertySource(getOptionParser().parse(args));
        ctx.getEnvironment().getPropertySources().addFirst(propertySource);
    }

    private static OptionParser getOptionParser() {
        OptionParser optionParser = new OptionParser();
        optionParser.accepts("format").withRequiredArg();
        optionParser.accepts("customerIds").withRequiredArg();
        optionParser.accepts("dateRange").withRequiredArg();
        optionParser.accepts("itemsFile").withRequiredArg();
        optionParser.accepts("itemsCount").withRequiredArg();
        optionParser.accepts("itemsQuantity").withRequiredArg();
        optionParser.accepts("eventsCount").withRequiredArg();
        optionParser.accepts("outDir").withRequiredArg();
        optionParser.accepts("broker").withRequiredArg();
        optionParser.accepts("queue").withRequiredArg();
        optionParser.accepts("topic").withRequiredArg();
        return optionParser;
    }
}
