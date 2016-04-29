package com.brilliantbear.gank.bean;

/**
 * Created by cx.lian on 2016/4/29.
 */
public class ImageSize {

    public int width;
    public int height;

    public ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public ImageSize() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
