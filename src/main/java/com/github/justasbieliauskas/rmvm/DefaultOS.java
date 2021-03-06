package com.github.justasbieliauskas.rmvm;

import com.github.justasbieliauskas.rmvm.cpu.MutableCPU;
import com.github.justasbieliauskas.rmvm.cpu.CPUAfterMasterCommand;

/**
 * Operating system with processors.
 *
 * TODO: test
 *
 * @author Justas Bieliauskas
 */
public class DefaultOS implements OS
{
    private final MutableCPU processor;

    private final CPUAfterMasterCommand newProcessor;

    /**
     * @param processor processor
     * @param newProcessor processor for commands
     */
    public DefaultOS(MutableCPU processor, CPUAfterMasterCommand newProcessor) {
        this.processor = processor;
        this.newProcessor = newProcessor;
    }

    @Override
    public void execute(String command) throws Exception {
        try {
            this.processor.update(
                this.newProcessor.with(this.processor, command).toMap()
            );
        } catch (Exception e) {
            throw new Exception(
                "Failed to execute command \"" + command + "\".",
                e
            );
        }
    }
}
