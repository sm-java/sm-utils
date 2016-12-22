/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sm.utils;

import java.nio.charset.Charset;

/**
 * @author smazumder6
 */
public class BitUtil {

    //Size of a byte in bytes
    public static final int SIZE_OF_BYTE = 1;
    //Size of a boolean in bytes
    public static final int SIZE_OF_BOOLEAN = 1;

    //Size of a char in bytes
    public static final int SIZE_OF_CHAR = 2;
    //Size of a short in bytes
    public static final int SIZE_OF_SHORT = 2;

    //Size of a integer in bytes
    public static final int SIZE_OF_INTEGER = 4;
    //Size of a float in bytes
    public static final int SIZE_OF_FLOAT = 4;

    //Size of a long in bytes
    public static final int SIZE_OF_LONG = 8;
    //Size of a double in bytes
    public static final int SIZE_OF_DOUBLE = 8;

    //length of the data block used by the CPU cache subsystem in bytes
    public static final int CACHE_LINE_LENGHT = 64;

    public static final byte[] HEX_DIGIT_TABLE = {
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'
    };

    private static final byte[] FROM_HEX_DIGIT_TABLE = new byte[128];

    static {

        FROM_HEX_DIGIT_TABLE['0'] = 0x00;
        FROM_HEX_DIGIT_TABLE['1'] = 0x01;
        FROM_HEX_DIGIT_TABLE['2'] = 0x02;
        FROM_HEX_DIGIT_TABLE['3'] = 0x03;
        FROM_HEX_DIGIT_TABLE['4'] = 0x04;
        FROM_HEX_DIGIT_TABLE['5'] = 0x05;
        FROM_HEX_DIGIT_TABLE['6'] = 0x06;
        FROM_HEX_DIGIT_TABLE['7'] = 0x07;
        FROM_HEX_DIGIT_TABLE['8'] = 0x08;
        FROM_HEX_DIGIT_TABLE['9'] = 0x09;
        FROM_HEX_DIGIT_TABLE['a'] = 0x0a;
        FROM_HEX_DIGIT_TABLE['A'] = 0x0a;
        FROM_HEX_DIGIT_TABLE['b'] = 0x0b;
        FROM_HEX_DIGIT_TABLE['B'] = 0x0b;
        FROM_HEX_DIGIT_TABLE['c'] = 0x0c;
        FROM_HEX_DIGIT_TABLE['C'] = 0x0c;
        FROM_HEX_DIGIT_TABLE['d'] = 0x0d;
        FROM_HEX_DIGIT_TABLE['D'] = 0x0d;
        FROM_HEX_DIGIT_TABLE['e'] = 0x0e;
        FROM_HEX_DIGIT_TABLE['E'] = 0x0e;
        FROM_HEX_DIGIT_TABLE['f'] = 0x0f;
        FROM_HEX_DIGIT_TABLE['F'] = 0x0f;
    }

    private static final int LAST_DIGIT_MASK = 0b1;

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /**
     *
     * @param value
     * @return
     */
    public static int findNextPositivePowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    /**
     * Align a value to the next multiple up of alignment. If the value equals
     * an alignment multiple then it is returned unchanged.
     * <p>
     * This method executes without branching.
     *
     * @param value
     * @param alignment
     * @return
     */
    public static int align(final int value, final int alignment) {
        return (value + (alignment - 1)) & ~(alignment - 1);
    }

    /**
     * Generate a byte array from the hex representation of the given byte
     * array.
     *
     * @param buffer
     * @return
     */
    public static byte[] fromHexByteArray(final byte[] buffer) {
        final byte[] outputBuffer = new byte[buffer.length >> 1];

        for (int i = 0; i < buffer.length; i += 2) {
            outputBuffer[i >> 1]
                    = (byte) ((FROM_HEX_DIGIT_TABLE[buffer[i]] << 4)
                    | FROM_HEX_DIGIT_TABLE[buffer[i + 1]]);

        }
        return outputBuffer;
    }

    /**
     * Generate a byte array that is a hex representation of a given byte array.
     *
     * @param buffer to convert to a hex representation
     * @return new byte array that is hex representation (in Big Endian) of the
     * passed array
     */
    public static byte[] toHexByteArray(final byte[] buffer) {
        return toHexByteArray(buffer, 0, buffer.length);
    }

    /**
     * Generate a byte array that is a hex representation of a given byte array.
     *
     * @param buffer
     * @param offset
     * @param length
     * @return
     */
    public static byte[] toHexByteArray(final byte[] buffer, int offset, int length) {
        final byte[] outputBuffer = new byte[length << 1];

        for (int i = 0; i < (length << 1); i += 2) {

            final byte b = buffer[offset + (i >> 1)];

            outputBuffer[i] = HEX_DIGIT_TABLE[(b >> 4) & 0x0F];
            outputBuffer[i + 1] = HEX_DIGIT_TABLE[b & 0x0F];
        }
        return outputBuffer;
    }
    
    /**
     * Generate a byte array from as string that is the hex representation of the 
     * given byte array
     * @param str
     * @return 
     */
    public static byte[] fromHex(final String str) {
        return fromHexByteArray(str.getBytes(UTF8_CHARSET));
        
    }
    
    /**
     * Generate a string that is a hex representation of a given byte array.
     * @param buffer
     * @return 
     */
    public static String toHex(final byte[] buffer) {
        return new String(toHexByteArray(buffer), UTF8_CHARSET);
    }
    
    /**
     * Returns true is a number is even, false otherwise.
     * @param num
     * @return 
     */
    public static boolean isEven(final int num) {
        return (num & LAST_DIGIT_MASK) == 0;        
    }
    
    /**
     * Is a value a positive value of 2.
     * 
     * @param value
     * @return 
     */
    public boolean isPowerOfTwo(final int value) {
       return value > 0 && ((value & (~value + 1)) == 0) ;
    }
}
