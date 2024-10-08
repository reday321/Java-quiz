import java.util.*;
import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class QuizSystem {

    static Scanner sc = new Scanner(System.in);
    static JSONArray quizArray = new JSONArray();

    public static void main(String[] args) throws Exception {
        loadQuiz();
        String role = login();

        if (role.equals("admin")) {
            adminInterface();
        } else if (role.equals("student")) {
            studentInterface();
        }
    }

    // Load users.json for login
    public static String login() throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray usersArray = (JSONArray) parser.parse(new FileReader("users.json"));

        System.out.println("System:> Enter your username");
        String username = sc.nextLine();

        System.out.println("System:> Enter password");
        String password = sc.nextLine();

        for (Object obj : usersArray) {
            JSONObject user = (JSONObject) obj;
            if (user.get("username").equals(username) && user.get("password").equals(password)) {
                System.out.println("System:> Welcome " + user.get("role") + "!");
                return (String) user.get("role");
            }
        }
        System.out.println("Invalid credentials. Try again.");
        return login();
    }

    // Admin Interface to add new questions
    public static void adminInterface() throws IOException {
        while (true) {
            System.out.println("System:> Input your question");
            String question = sc.nextLine();

            System.out.println("System: Input option 1:");
            String option1 = sc.nextLine();

            System.out.println("System: Input option 2:");
            String option2 = sc.nextLine();

            System.out.println("System: Input option 3:");
            String option3 = sc.nextLine();

            System.out.println("System: Input option 4:");
            String option4 = sc.nextLine();

            System.out.println("System: What is the answer key?");
            int answerKey = sc.nextInt();
            sc.nextLine();  // consume newline

            JSONObject newQuestion = new JSONObject();
            newQuestion.put("question", question);
            newQuestion.put("option 1", option1);
            newQuestion.put("option 2", option2);
            newQuestion.put("option 3", option3);
            newQuestion.put("option 4", option4);
            newQuestion.put("answerkey", answerKey);

            quizArray.add(newQuestion);

            saveQuiz();

            System.out.println("System:> Saved successfully! Do you want to add more questions? (press 's' for start and 'q' for quit)");
            String next = sc.nextLine();
            if (next.equals("q")) {
                break;
            }
        }
    }

    // Student Interface to take quiz
    public static void studentInterface() {
        System.out.println("System:> We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
        String start = sc.nextLine();
        if (start.equals("s")) {
            int score = 0;
            for (int i = 0; i < 10; i++) {
                JSONObject question = (JSONObject) quizArray.get(new Random().nextInt(quizArray.size()));

                System.out.println("[Question " + (i + 1) + "] " + question.get("question"));
                System.out.println("1. " + question.get("option 1"));
                System.out.println("2. " + question.get("option 2"));
                System.out.println("3. " + question.get("option 3"));
                System.out.println("4. " + question.get("option 4"));

                System.out.print("Student:> ");
                int answer = sc.nextInt();
                sc.nextLine(); // consume newline

                if (answer == ((Long) question.get("answerkey")).intValue()) {
                    score++;
                }
            }

            showResult(score);
        }
    }

    // Display the final result
    public static void showResult(int score) {
        if (score >= 8) {
            System.out.println("Excellent! You have got " + score + " out of 10");
        } else if (score >= 5) {
            System.out.println("Good. You have got " + score + " out of 10");
        } else if (score >= 2) {
            System.out.println("Very poor! You have got " + score + " out of 10");
        } else {
            System.out.println("Very sorry you are failed. You have got " + score + " out of 10");
        }

        System.out.println("Would you like to start again? Press 's' for start or 'q' for quit.");
        String next = sc.nextLine();
        if (next.equals("s")) {
            studentInterface();
        }
    }

    // Load quiz questions from quiz.json
    public static void loadQuiz() {
        try {
            JSONParser parser = new JSONParser();
            quizArray = (JSONArray) parser.parse(new FileReader("quiz.json"));
        } catch (Exception e) {
            System.out.println("Error loading quiz: " + e.getMessage());
        }
    }

    // Save the quiz questions to quiz.json
    public static void saveQuiz() throws IOException {
        FileWriter file = new FileWriter("quiz.json");
        file.write(quizArray.toJSONString());
        file.flush();
        file.close();
    }
}
