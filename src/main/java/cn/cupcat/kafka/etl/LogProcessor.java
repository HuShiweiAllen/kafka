package cn.cupcat.kafka.etl;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

/**
 * @Description: java类作用描述
 * @Author: zxy
 * @CreateDate: 2018/5/30 16:07
 * @Version: 1.0
 */
public class LogProcessor implements Processor<byte[], byte[]> {

    private ProcessorContext context;

    /**
     * Initialize this processor with the given context. The framework ensures this is called once per processor when the topology
     * that contains it is initialized.
     * <p>
     * If this processor is to be {@link #punctuate(long) called periodically} by the framework, then this method should
     * {@link ProcessorContext#schedule(long) schedule itself} with the provided context.
     *
     * @param context the context; may not be null
     */
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    /**
     * Process the record with the given key and value.
     *
     * @param key   the key for the record
     * @param value the value for the record
     */
    @Override
    public void process(byte[] key, byte[] value) {
        String line = new String(value);

        if (line.contains("<<<")) {
            line = line.split("<<<")[1].trim();
        }
        context.forward("logProcceror".getBytes(),line.getBytes());
    }

    /**
     * Perform any periodic operations, if this processor {@link ProcessorContext#schedule(long) schedule itself} with the context
     * during {@link #init(ProcessorContext) initialization}.
     *
     * @param timestamp the stream time when this method is being called
     */
    @Override
    public void punctuate(long timestamp) {

    }

    /**
     * Close this processor and clean up any resources. Be aware that {@link #close()} is called after an internal cleanup.
     * Thus, it is not possible to write anything to Kafka as underlying clients are already closed.
     * <p>
     * Note: Do not close any streams managed resources, like {@link StateStore}s here, as they are managed by the library.
     */
    @Override
    public void close() {
    }
}
