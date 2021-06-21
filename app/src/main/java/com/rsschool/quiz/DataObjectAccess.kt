package com.rsschool.quiz

// Этот класс реализует доступ к базе данных, в которой хранятся User и QuizObject объекты
class DataObjectAccess {

    // Номер вопроса - Номер ответа
    private val numAnswers: HashMap<Int, Int> = hashMapOf()

    // hashMap позволяет нам удобно перезаписывать уже выбранные ответы
    fun addAnswer(numberQuest: Int, numberAnswer: Int) {
        numAnswers[numberQuest] = numberAnswer
    }

    fun countAnswer(): Int {
        return numAnswers.size
    }

    fun getResultMessage(): User {
        var result = 0
        var history = "История вопросов и ответов.\n"

        for (x in 0..4) {
            var y = numAnswers[x+1]!!
            if (quizGames[x].numberCorrectAnswer == numAnswers[x + 1]) ++result
            history += "\nВопрос: ${quizGames[x].question}\nВаш ответ: ${quizGames[x].answers[y - 1]}\nПравильный ответ: ${quizGames[x].answers[quizGames[x].numberCorrectAnswer - 1]}"
        }

        return User("jesus@haven.com", "Результат квиза: $result из 5.", history)
    }

    // Таблица БД
    private val quizGames: List<QuizObject> = listOf(
        QuizObject("Вы готовы, дети?", listOf("Да, капитан!", "Да!", "Нет.", "Нет, капитан", "буль-буль-буль"), 1, 1),
        QuizObject("Ктоооооооо... Кто проживает на дне океана?", listOf("Рыбки", "Губка Боб Квадратные Штаны!", "Водолаз", "Никто не проживает", "Terror from the Deep"), 2,2),
        QuizObject("Жёлтая губка, малыш без изъяна?", listOf("С изъяном", "Не малышь", "Губка Боб Квадратные Штаны!", "Синяя губка", "Жёлтая подводная лодка"), 3,3),
        QuizObject("Кто побеждает всегда и везде?", listOf("Годзилла", "Рэмбо", "Капитан Америка", "Губка Боб Квадратные Штаны!", "Фиолетовый из Повер Рэнджерс"), 4,4),
        QuizObject("Кто также ловок, как рыба в воде?", listOf("Другая рыба", "Аквамен", "Глубина", "Тазик залитый бетоном", "Губка Боб Квадратные Штаны!"), 5,5),
        )

    // Патерн Null-объект
    private val nullQuizObject = QuizObject("nullQuizObject", listOf("nullQuizObject", "nullQuizObject", "nullQuizObject", "nullQuizObject", "nullQuizObject"), 1,1)


    fun getQuizObject(num: Int) : QuizObject {
        return if (num <= quizGames.lastIndex) quizGames[num] else nullQuizObject
    }

    fun getSize() : Int = quizGames.lastIndex + 1

}