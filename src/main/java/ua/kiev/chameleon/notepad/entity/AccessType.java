package ua.kiev.chameleon.notepad.entity;

public enum AccessType {
    PRIVATE("private"), PUBLIC("public"),REGISTER("register");
    private final String type;
    AccessType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
