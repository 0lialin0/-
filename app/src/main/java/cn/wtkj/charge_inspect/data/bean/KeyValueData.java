package cn.wtkj.charge_inspect.data.bean;

/**
 * Created by Desktop17A68 on 2015/9/22.
 */
public class KeyValueData {

    private String id;
    private String value;

    public KeyValueData(String id, String value) {
        super();
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
