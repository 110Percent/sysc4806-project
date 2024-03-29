/**
 * javascript file for generating survey creation structure
 * @author Ethan Houlahan, 101145675
 */

let questionCount = 0; //Number of questions within survey, used for generating id of question div

class Survey { //survey class for processing
    constructor(surveyTitle, surveyQuestions) {
        this.surveyTitle = surveyTitle;
        this.surveyQuestions = surveyQuestions;
    }
}

class Question { //General Question class for processing, used for text questions
    constructor(query, questionType) {
        this.query = query, this.questionType = questionType
    }
}

class MultiQuestion extends Question { //Multiple choice Question class for processing
    constructor(query, potentialAnswers, responseType) {
        super(query, responseType);
        this.potentialAnswers = potentialAnswers;
    }
}

class NumericQuestion extends Question { //Numeric Question class for processing
    constructor(query, min, max, responseType) {
        super(query, responseType);
        this.min = parseInt(min);
        this.max = parseInt(max);
    }
}

/**
 * create a new answer element for a multiple choice question
 * @param event
 */
function newAnswer(event) {
    let div = event.currentTarget.parentElement; //select question div to add answer to
    div.appendChild(document.createElement("br"));
    let questionAnswer = document.createElement("input"); //create input for answer value
    questionAnswer.setAttribute("type", 'text');
    questionAnswer.setAttribute("value", `Answer`);
    questionAnswer.classList.add("multipleAnswer", "round-input"); //class value assigned for processing
    div.appendChild(questionAnswer); //append element

}

/**
 * remove an answer from a multiple choice question
 * @param event
 */
function removeAnswer(event) {
    let div = event.currentTarget.parentElement;
    if (div.lastChild.nodeName != "BUTTON") { //ensure no removal of button element

        if (div.lastChild.nodeName == "INPUT") { //ensure input and then break are removed
            div.lastChild.remove();
        }
        if (div.lastChild.nodeName == "BR") {
            div.lastChild.remove();
        }
    }
}

/**
 * remove a given question
 * @param event
 */
function removeQuestion() {
    let lastQuestion = document.getElementById("question_" + (questionCount - 1).toString()); // select last question element
    try {
        lastQuestion.remove(); //remove question element
        questionCount--; //deincrement question count
    } catch (err) {
        alert("No Questions Found!") // if no questions found alert the user
    }
}

/**
 * Front end filtering of number range for numeric select question max values
 * @param maxElem
 */
function numRangeCheckMax(event) {

    let max_id = event.target.id;
    let min_id = max_id.replace("max", "min");

    let minElem = document.getElementById(min_id); //get associated min element
    let minVal = parseInt(minElem.value); //get value of other element
    if (parseInt(event.target.value) <= minVal) {
        minElem.value = parseInt(event.target.value) - 1; // ensure min is below max value in range
    }

}

/**
 * Front end filtering of number range for numeric select question min values
 * @param maxElem
 */
function numRangeCheckMin(event) {

    let min_id = event.target.id;
    let max_id = min_id.replace("min", "max");

    let maxElem = document.getElementById(max_id); //get associated max element
    let maxVal = parseInt(maxElem.value); //get value of other element
    if (maxVal <= parseInt(event.target.value)) {
        event.target.value = maxVal - 1; // ensure min is below max value in range
    }
}

/**
 * Create New Question
 * @param event
 */
function newQuestion() {
    let questionType = $("select#question_type option:selected").val(); //get type of question to create

    let questionDiv = document.createElement("div"); //creating div
    questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
    questionDiv.classList.add('question-creation-div')

    let questionLabel = document.createElement("label"); //creating label for text question
    questionLabel.setAttribute('for', `question_text_${questionCount.toString()}`);
    questionLabel.textContent = `Question ${questionCount + 1}`
    questionDiv.appendChild(questionLabel);

    questionDiv.appendChild(document.createElement('br'))

    if (questionType == 'text') { //Create text type question
        questionDiv.classList.add("text");
        document.querySelector('#new_questions_box').appendChild(questionDiv);

        let questionText = document.createElement("input"); //creating input for text question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("id", `question_text_${questionCount.toString()}`);
        questionText.setAttribute("name", `question_text_${questionCount.toString()}`);
        questionText.setAttribute("value", `Question ${questionCount + 1}`);
        questionText.classList.add("question", "round-input"); //class value assigned for processing
        questionDiv.appendChild(questionText);
    } else if (questionType == 'numeric') { //Create numeric type question
        questionDiv.classList.add("numeric");
        document.querySelector('#new_questions_box').appendChild(questionDiv);

        let questionText = document.createElement("input"); //creating input for numeric question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name", `question_text_${questionCount.toString()}`);
        questionText.classList.add("question", "round-input"); //class value assigned for processing
        questionText.setAttribute("value", `Question ${questionCount + 1}`);
        questionDiv.appendChild(questionText);

        questionDiv.appendChild(document.createElement("br"));

        let questionMinValueLabel = document.createElement("label"); //Max value label
        questionMinValueLabel.innerText = "Minimum Value:";
        questionMinValueLabel.classList.add("descriptor");
        questionDiv.appendChild(questionMinValueLabel);

        let questionMinValue = document.createElement("input"); //creating input for minimum value in numeric range
        questionMinValue.setAttribute("type", 'number');
        questionMinValue.setAttribute("name", `question_min_value_${questionCount.toString()}`);
        questionMinValue.setAttribute("id", `question_min_value_${questionCount.toString()}`);
        questionMinValue.setAttribute("value", "0");
        questionMinValue.classList.add("minimum", "round-input"); //class value assigned for processing
        questionDiv.appendChild(questionMinValue);
        questionDiv.appendChild(document.createElement("br"));

        let questionMaxValueLabel = document.createElement("label"); //Max value label
        questionMaxValueLabel.innerText = "Maximum Value:";
        questionMaxValueLabel.classList.add("descriptor");
        questionDiv.appendChild(questionMaxValueLabel);

        let questionMaxValue = document.createElement("input"); //creating input for maximum value in numeric range
        questionMaxValue.setAttribute("type", 'number');
        questionMaxValue.setAttribute("name", `question_max_value_${questionCount.toString()}`);
        questionMaxValue.setAttribute("id", `question_max_value_${questionCount.toString()}`);
        questionMaxValue.setAttribute("value", "1");
        questionMaxValue.classList.add("maximum", "round-input"); //class value assigned for processing
        questionDiv.appendChild(questionMaxValue);


        questionMinValue.addEventListener('focusout', numRangeCheckMin);

        questionMaxValue.addEventListener('focusout', numRangeCheckMax);

    } else if (questionType == 'multiple') { //Create multiple choice type question
        questionDiv.classList.add("multiple"); //class value assigned for processing
        document.querySelector('#new_questions_box').appendChild(questionDiv);

        let questionText = document.createElement("input");  //creating input for multiple choice question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name", `question_text_${questionCount.toString()}`);
        questionText.setAttribute("value", `Question ${questionCount + 1}`);
        questionText.classList.add("question", "round-input"); //class value assigned for processing
        questionDiv.appendChild(questionText);

        questionDiv.appendChild(document.createElement("br"));

        let newAnswerButton = document.createElement("button"); //create button for new addition of answer elements
        newAnswerButton.setAttribute("id", `new_answer_question_${questionCount.toString()}`);
        newAnswerButton.addEventListener('click', newAnswer);
        newAnswerButton.classList.add("button", "blue");
        newAnswerButton.innerText = "Add New Answer";
        questionDiv.appendChild(newAnswerButton);

        let removeAnswerButton = document.createElement("button");  //create button for removal of answer elements
        removeAnswerButton.setAttribute("id", `rem_answer_question_${questionCount.toString()}`);
        removeAnswerButton.addEventListener('click', removeAnswer);
        removeAnswerButton.classList.add("button", "red");
        removeAnswerButton.innerText = "Remove Answer";
        questionDiv.appendChild(removeAnswerButton);
    }
    questionCount++;
}

function surveySubmit() {
    let surveyName = document.getElementById("survey_name").value;
    let surveyVar = new Survey(surveyName, []); //create survey variable object
    let tempQuestions = document.querySelectorAll("div");
    for (let i = 1; i < tempQuestions.length; i++) {
        if (tempQuestions[i].classList.contains("text")) {
            let tempQuery = tempQuestions[i].querySelector(".question").value;
            surveyVar.surveyQuestions.push(new Question(tempQuery, "WRITTEN")); //Add question class to survey
        }
        if (tempQuestions[i].classList.contains("multiple")) {
            let tempQuery = tempQuestions[i].querySelector(".question").value; //process question
            const tempAnswers = [];
            let tempAnswersIterator = tempQuestions[i].querySelectorAll(".multipleAnswer");
            for (let j = 0; j < tempAnswersIterator.length; j++) { //process all answers
                tempAnswers.push(tempAnswersIterator[j].value);
            }
            surveyVar.surveyQuestions.push(new MultiQuestion(tempQuery, tempAnswers, "MULTISELECT")); //Add question class to survey
        }
        if (tempQuestions[i].classList.contains("numeric")) {
            let tempQuery = tempQuestions[i].querySelector(".question").value; //process question
            let tempMin = tempQuestions[i].querySelector(".minimum").value; //process min
            let tempMax = tempQuestions[i].querySelector(".maximum").value; //process max
            surveyVar.surveyQuestions.push(new NumericQuestion(tempQuery, tempMin, tempMax, "NUMERIC")); //Add question class to survey
        }
    }
    let surveyJSON = JSON.stringify(surveyVar, null)
    console.log(surveyJSON);
    $.ajax //create POST request
        ({
            contentType: 'application/json',
            type: "POST",
            url: "/createsurvey/process",
            dataType: "json",
            data: surveyJSON,
            success: function () {
                // Redirect user to dashboard on successful creation
                window.location.replace("/")
            },
            error: function () {
                const body = $('body')
                body.empty();
                body.append("<h2>An error occurred when submitting your survey</h2>");
                body.append("<a href='/'>Return To Dashboard</a>")
            }
        });
}

//assign event listeners to buttons
document.getElementById("new_question").addEventListener('click', newQuestion);
document.getElementById('rem_question').addEventListener('click', removeQuestion);
document.getElementById("survey_submit").addEventListener('click', surveySubmit);
