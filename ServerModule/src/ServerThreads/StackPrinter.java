package ServerThreads;

public class StackPrinter
        implements Thread.UncaughtExceptionHandler{

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StackPrinter.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error(t.getName(), e);
    }
}
