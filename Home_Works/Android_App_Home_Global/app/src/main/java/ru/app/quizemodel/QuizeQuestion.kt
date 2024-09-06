package ru.app.quizemodel

class QuizeQuestion(private val question: String, private val answers: List<String>,
                    private val correctAnswer: Int
) {

    fun question(): String {
        return question
    }

    fun answers(): List<String> {
        return answers
    }

    fun correctAnswer(): Int{
        return correctAnswer
    }


}