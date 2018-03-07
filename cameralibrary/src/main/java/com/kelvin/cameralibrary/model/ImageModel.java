package com.kelvin.cameralibrary.model;

/**
 * Authority: Asus
 * Date: 2018-03-06  15:02
 */

public class ImageModel {
    private String id;//图片id
    private String path;//路径
    private Boolean isChecked = false;//是否被选中

    public ImageModel(String id, String path, Boolean isChecked) {
        this.id = id;
        this.path = path;
        this.isChecked = isChecked;
    }

    public ImageModel(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }
}
