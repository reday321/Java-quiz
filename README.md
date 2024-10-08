# Quiz System

This project is a simple Java-based quiz system that allows an admin to create and manage multiple-choice questions (MCQs) and enables students to take quizzes based on the questions stored in a JSON file.

## Features

- **Admin Login**: Admin users can log in to the system and add new questions to the quiz bank.
- **Student Login**: Students can log in to take quizzes generated from the quiz bank.
- **Question Management**: Admins can create, save, and manage multiple-choice questions in a JSON format.
- **Random Question Generation**: Students are presented with a random selection of questions for their quizzes.
- **Scoring System**: The system calculates and displays scores based on student responses.

## Project Structure

- **src/**: Contains the Java source code files.
  - `QuizSystem.java`: The main class that runs the application.
- **quiz.json**: A JSON file that stores the multiple-choice questions and answers.
- **users.json**: A JSON file that stores user credentials (both admin and student).
