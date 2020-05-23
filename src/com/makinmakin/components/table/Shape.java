package com.makinmakin.components.table;

/**
 * Sebuah kelas abstrak Shape2D.
 */
public abstract class Shape {
    
    /**
     *  Panjang bentuk.
     */
    protected int height;

    /**
     *  Lebar bentuk.
     */
    protected int width;

    /**
     *  Luas sebuah bentuk.
     */
    public abstract int luas();
    
    /**
     *  Keliling sebuah bentuk.
     */
    public abstract int keliling();
}