package ua.kiev.chameleon.notepad.entity;

public enum NoteColor {
    WHITE("FFFFFF");
    private final String color;

    NoteColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
}
