package com.note.JavaDevModul13;

import com.note.JavaDevModul13.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NoteService {
    private final List<Note> noteList = new CopyOnWriteArrayList<>();
    private final Random random = new Random();

    public List<Note> listAll(){
        return noteList;
    }

    public Note add(Note note){
        note.setId(random.nextLong());
        noteList.add(note);
        return note;
    }

    public void deleteById(long id){
        if (!noteList.removeIf(note -> note.getId() == id)){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(Note note){
        if (!noteList.contains(note)){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        for (Note n : noteList) {
            if (n.getId() == note.getId()){
                n.setTitle(note.getTitle());
                n.setContent(note.getContent());
            }
        }
    }

    public Note getById(long id){
        try {
            return noteList.stream().filter(note -> note.getId() == id).findFirst().orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
