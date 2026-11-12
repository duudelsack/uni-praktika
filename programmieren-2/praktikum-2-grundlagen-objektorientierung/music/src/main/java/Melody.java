public class Melody {
  private Note firstNote;

  public Melody(Note firstNote) {
    this.firstNote = firstNote;
  }

  /**
   * Iterates over the Linked list of notes and returns true if found
   *
   * @param note Note to check if it is contained
   * @return is the note in the Melody
   */
  public boolean contains(Note note) {
    for (Note currentNote = this.getFirstNote(); currentNote != null; currentNote = currentNote.getNext()) {
      if (currentNote.equals(note)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Inserts the note into the Melody if it is new
   *
   * @param note added to the Melody
   * @throws IllegalArgumentException if the note is already contained in the melody
   */
  public void addNote(Note note) {
    //check if note is contained in Melody
    if (this.contains(note)) {
      throw new IllegalArgumentException("Note already contained in Melody");
    }
    Note lastNote = this.getLastNote();
    if (lastNote != null) {
      lastNote.setNextNote(note);
    } else {
      this.firstNote = note;
    }
  }

  /**
   * @param newNote Note to be inserted
   * @param index   of note to be replaced
   * @throws IllegalArgumentException  if Note is already in Melody
   * @throws IndexOutOfBoundsException if index is invalid
   */
  public void replaceNote(Note newNote, int index) {
    if (this.contains(newNote)) {
      throw new IllegalArgumentException("Note already contained in Melody");
    }
    if (index < 0) {
      // negative indexes are impossible
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) {
      Note firstNote = this.getFirstNote();
      if (firstNote == null) {
        // handle there not being a first note;
        this.firstNote = newNote;
        return;
      }
      //replace the first note
      newNote.setNextNote(firstNote.getNext());
      this.firstNote = newNote;
      return;
    }
    int i = 0;
    for (Note currentNote = this.getFirstNote(); currentNote != null && i <= index; currentNote = currentNote.getNext(), i++) {
      if (i == index - 1 && currentNote.getNext() != null) {
        Note noteAfter = currentNote.getNext();
        if (noteAfter != null) {
          newNote.setNextNote(noteAfter.getNext());
        }
        currentNote.setNextNote(newNote);
        return;
      }
    }
    throw new IndexOutOfBoundsException();
  }

  @Override
  public String toString() {
    if (this.getFirstNote() == null) {
      return "";
    }
    StringBuilder string = new StringBuilder();
    for (Note currentNote = this.getFirstNote(); currentNote != null; currentNote = currentNote.getNext()) {
      string.append(currentNote);
      if (currentNote.getNext() != null) {
        string.append(",");
      }
    }
    return string.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Melody melody)) {
      return false;
    }
    return this.toString().equals(melody.toString());
  }

  /**
   * @return first Note
   */
  public Note getFirstNote() {
    return this.firstNote;
  }

  /**
   * @return the last Note or null
   */
  public Note getLastNote() {
    for (Note note = firstNote; note != null; note = note.getNext()) {
      // iterate to last Note
      if (note.getNext() == null) {
        return note;
      }
    }
    return null;
  }
}