package cn.edu.nbu.common.enums;

/**
 * Created by Administrator on 2018/6/5 0005.
 */
public enum AnnotationTypeEnum {
    A(1,"A"),B(2,"B");
    private Integer code;
    private String desc;

    AnnotationTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
