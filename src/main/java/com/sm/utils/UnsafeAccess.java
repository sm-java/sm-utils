/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bb.utils;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/**
 *
 * @author smazumder6
 */
public class UnsafeAccess {

    public static final Unsafe UNSAFE;

    static {

        try {
            final PrivilegedExceptionAction<Unsafe> action = () -> {
                final Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);

                return (Unsafe) f.get(null);
            };

            UNSAFE = AccessController.doPrivileged(action);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}

