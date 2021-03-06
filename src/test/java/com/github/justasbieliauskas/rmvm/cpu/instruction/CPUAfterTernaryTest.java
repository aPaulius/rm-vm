package com.github.justasbieliauskas.rmvm.cpu.instruction;

import com.github.justasbieliauskas.rmvm.WordEquality;
import com.github.justasbieliauskas.rmvm.data.CPURegister;
import org.junit.Test;

/**
 * Test for {@link CPUAfterTernary}.
 *
 * @author Justas Bieliauskas
 */
public class CPUAfterTernaryTest
{
    @Test
    public void onTrue() {
        new WordEquality(
            new CPURegister(
                new CPUAfterTernary(true, "A", 5, 14),
                "A"
            ),
            5
        ).test();
    }

    @Test
    public void onFalse() {
        new WordEquality(
            new CPURegister(
                new CPUAfterTernary(false, "A", 66, 28),
                "A"
            ),
            28
        ).test();
    }
}