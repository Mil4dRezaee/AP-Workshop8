# Note Management System

This is a Java-based mini project for managing notes. The system allows users to add, remove, view, and export notes. It uses serialization to store notes locally and provides a simple CLI interface for interaction.

## Features

- **Add Notes**: Create and save notes with a title, content, and optional date.
- **Remove Notes**: Delete notes by selecting them from a list.
- **View Notes**: Display a list of notes with their titles and creation dates, and view specific note content.
- **Export Notes**: Export a note to a `.txt` file in the `export` directory.
- **Persistence**: Notes are stored in the `assets` directory using serialization for easy retrieval.
- **Error Handling**: Handles duplicate note titles gracefully with a custom exception.

## Project Structure

- `Note.java`: Represents a note with attributes like title, content, and date.
- `DuplicateTitleException.java`: Custom exception to handle duplicate note titles.
- `Command.java`: Core logic for managing notes, including adding, removing, exporting, and displaying notes.
- `Main.java`: The entry point of the application, providing a CLI for user interaction.

## Requirements

- Java 8 or later
- `assets/` directory for serialized note storage (automatically created if missing)
- `export/` directory for exported text files (automatically created if missing)
