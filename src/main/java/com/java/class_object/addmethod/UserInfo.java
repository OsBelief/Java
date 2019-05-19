package com.java.class_object.addmethod;

public class UserInfo extends BaseModel {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private void privateMethod() {

    }

    protected void protectedMethod() {

    }

    void defaultMethod() {

    }

    @Override
    public void superAbstractMethod() {

    }

    @Override
    public void superPublicMethod() {
        super.superPublicMethod();
    }
}
