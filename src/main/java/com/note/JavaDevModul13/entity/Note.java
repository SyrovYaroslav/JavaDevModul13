package com.note.JavaDevModul13.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class Note {
    private long id;
    private String title;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
