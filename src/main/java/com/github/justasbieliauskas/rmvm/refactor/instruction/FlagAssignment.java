package com.github.justasbieliauskas.rmvm.refactor.instruction;

import com.github.justasbieliauskas.rmvm.refactor.data.*;

/**
 * Flag modification in status register.
 * For possible flag identifiers, see {@link FlagIndex}.
 *
 * @author Justas Bieliauskas
 */
public class FlagAssignment implements Instruction
{
    private final RegisterAssignment instruction;

    /**
     * Constructor for changing status register.
     *
     * @param status status register
     * @param id flag identifier
     * @param to1 should flag be changed to 1 (true) or 0 (false)
     */
    public FlagAssignment(Word status, char id, Condition to1) {
        this(status, new RegisterIndex("ST"), 0, new FlagIndex(id), to1);
    }

    /**
     * Constructor used in testing.
     *
     * @param word word to change flag on as integer
     * @param byteIndex byte index in word as integer
     * @param flagIndex flag index in byte as integer
     * @param to1 should flag be changed to 1 (true) or 0 (false) as boolean
     */
    FlagAssignment(int word, int byteIndex, int flagIndex, boolean to1) {
        this(() -> word, () -> 0, byteIndex, () -> flagIndex, () -> to1);
    }

    private FlagAssignment(
        Word status,
        Index registerIndex,
        int byteIndex,
        Index flagIndex,
        Condition to1
    ) {
        this.instruction = new RegisterAssignment(
            registerIndex,
            new WordWithByte(
                status,
                byteIndex,
                new ByteWithFlag(new WordByte(status, byteIndex), flagIndex, to1)
            )
        );
    }

    @Override
    public void change(Register[] processor, Memory memory) {
        this.instruction.change(processor, memory);
    }
}
