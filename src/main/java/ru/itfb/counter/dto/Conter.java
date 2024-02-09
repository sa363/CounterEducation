/*
 * Copyright (C) 2024 ITFB Group. - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 */

package ru.itfb.counter.dto;


public class Conter {
    private int count;

    public Conter() {
        this.count = 0;
    }

    /**
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Задание счетчика
     * @param count
     *
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Увеличивает count на 1
     */
    public void intcrementCount() {
        this.count++;
    }
}
