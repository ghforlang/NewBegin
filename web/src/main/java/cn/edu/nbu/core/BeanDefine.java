package cn.edu.nbu.core;

/**
 * Created by Administrator on 2018/6/6 0006.
 */
public class BeanDefine {
    private String id;
    private String clazz;

    public BeanDefine(String id, String clazz) {
        this.id = id;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
