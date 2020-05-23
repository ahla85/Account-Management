package com.makinmakin.components.table;

/**
 * Mendefinisikan kelas persegi panjang.
 * 
 * @extends {@link Shape}
 */
public class Rectangle extends Shape {

    /**
     *  Membuat objek Rectangle.
     */
    public Rectangle() {
        setHeight(0);
        setWidth(0);
    }

    /**
     *  Membuat objek Rectangle dengan insialisasi panjang dan lebar.
     */
    public Rectangle(int height, int width) {
        setHeight(height);
        setWidth(width);
    }

    public void setHeight(int height) {
        super.height = height;
    }

    public void setWidth(int width) {
        super.width = width;
    }

    public int getHeight() {
        return super.height;
    }

    public int getWidth() {
        return super.width;
    }

    @Override
    public int luas() {
        return height * width;
    }

    @Override
    public int keliling() {
        return 2 * (height + width);
    }
}