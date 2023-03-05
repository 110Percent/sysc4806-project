/**
 * javascript file for generating survey creation structure
 * @author Ethan Houlahan, 101145675
 */

let questionCount = 0; //Number of questions within survey, used for generating id of question div

class Survey { //survey class for processing
    constructor(name,questions){
        this.name = name;
        this.questions = questions;
    }
}
class Question { //General Question class for processing, used for text questions
    constructor(question, order,type) {
        this.question = question,
        this.order = order,
        this.questionType = type
    }
}
class MultiQuestion extends Question{ //Multiple choice Question class for processing
    constructor(question,answers,order,type) {
        super(question,order,type);
        this.answers = answers;
    }
}

class NumericQuestion extends Question{ //Numeric Question class for processing
    constructor(question,min,max, order,type) {
        super(question,order,type);
        this.min = parseInt(min);
        this.max = parseInt(max);

    }
}

/**
 * create a new answer element for a multiple choice question
 * @param event
 */
function newAnswer(event){
    let div = event.currentTarget.parentElement; //select question div to add answer to
    div.appendChild(document.createElement("br"));
    let questionAnswer = document.createElement("input"); //create input for answer value
    questionAnswer.setAttribute("type", 'text');
    questionAnswer.setAttribute("value",`Answer`);
    questionAnswer.classList.add("multipleAnswer"); //class value assigned for processing
    div.appendChild(questionAnswer); //append element

}

/**
 * remove an answer from a multiple choice question
 * @param event
 */
function removeAnswer(event){
    let div = event.currentTarget.parentElement;
    if (div.lastChild.nodeName != "BUTTON"){ //ensure no removal of button element

        if (div.lastChild.nodeName == "INPUT"){ //ensure input and then break are removed
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
function removeQuestion(){
    let lastQuestion = document.getElementById("question_" + (questionCount - 1).toString()); // select last question element
    try {
        lastQuestion.remove(); //remove question element
        questionCount--; //deincrement question count
    }catch(err){
        alert("No Questions Found!") // if no questions found alert the user
    }

}

/**
 * Create New Question
 * @param event
 */
function newQuestion() {
    let questionType = $("select#question_type option:selected").val(); //get type of question to create

    if (questionType == 'text') { //Create text type question
        let questionDiv = document.createElement("div"); //creating div
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.classList.add("text");
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input"); //creating input for text question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        questionText.classList.add("question"); //class value assigned for processing
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

    }

    else if (questionType == 'numeric') { //Create numeric type question
        let questionDiv = document.createElement("div"); //creating div
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.setAttribute("style", `padding: 20px`);
        questionDiv.classList.add("numeric");
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input"); //creating input for numeric question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.classList.add("question"); //class value assigned for processing
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let questionMinValueLabel = document.createElement("label"); //Max value label
        questionMinValueLabel.innerText = "Minimum Value:";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMinValueLabel);

        let questionMinValue = document.createElement("input"); //creating input for minimum value in numeric range
        questionMinValue.setAttribute("type", 'number');
        questionMinValue.setAttribute("name",`question_min_value_${questionCount.toString()}`);
        questionMinValue.setAttribute("value","0")
        questionMinValue.classList.add("minimum"); //class value assigned for processing
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMinValue);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let questionMaxValueLabel = document.createElement("label"); //Max value label
        questionMaxValueLabel.innerText = "Maximum Value:";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValueLabel);

        let questionMaxValue = document.createElement("input"); //creating input for maximum value in numeric range
        questionMaxValue.setAttribute("type", 'number');
        questionMaxValue.setAttribute("name",`question_max_value_${questionCount.toString()}`);
        questionMaxValue.setAttribute("value","1");
        questionMaxValue.classList.add("maximum"); //class value assigned for processing
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValue);
    }

    else if (questionType == 'multiple') { //Create multiple choice type question
        let questionDiv = document.createElement("div"); //creating div
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.classList.add("multiple"); //class value assigned for processing
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");  //creating input for multiple choice question
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        questionText.classList.add("question"); //class value assigned for processing
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let newAnswerButton = document.createElement("button"); //create button for new addition of answer elements
        newAnswerButton.setAttribute("id",`new_answer_question_${questionCount.toString()}`);
        newAnswerButton.addEventListener('click',newAnswer);
        newAnswerButton.innerText = "Add New Answer";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(newAnswerButton);

        let removeAnswerButton = document.createElement("button");  //create button for removal of answer elements
        removeAnswerButton.setAttribute("id",`rem_answer_question_${questionCount.toString()}`);
        removeAnswerButton.addEventListener('click',removeAnswer);
        removeAnswerButton.innerText = "Remove Answer";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(removeAnswerButton);
    }
    questionCount++;
}

function surveySubmit(){ //processing and submission for a created survey
    let surveyName = document.getElementById("survey_name").value;
    let surveyVar = new Survey(surveyName, []); //create survey variable object
    let texts = document.querySelectorAll(".text");
    for (let i = 0; i < texts.length; i++){ //process all text questions
        let tempQuestion = texts[i].querySelector(".question").value;
        let tempOrder = parseInt(texts[i].id.replace("question_",''));
        console.log(JSON.stringify(new Question(tempQuestion),null));
        surveyVar.questions.push(new Question(tempQuestion,tempOrder,"text")); //Add question class to survey
    }
    let multiples = document.querySelectorAll(".multiple");
    for (let i = 0; i < multiples.length; i++){ //process all multiple choice questions
        let tempQuestion = multiples[i].querySelector(".question").value; //process question
        const tempAnswers = [];
        let tempAnswersIterator = multiples[i].querySelectorAll(".multipleAnswer");
        let tempOrder = parseInt(multiples[i].id.replace("question_",''));
        for (let j = 0; j < tempAnswersIterator.length; j++){ //process all answers
            tempAnswers.push(tempAnswersIterator[j].value);
        }
        console.log(JSON.stringify(new MultiQuestion(tempQuestion,tempAnswers),null));
        surveyVar.questions.push(new MultiQuestion(tempQuestion,tempAnswers,tempOrder,"multiple")); //Add question class to survey
    }
    let numerics = document.querySelectorAll(".numeric");
    for (let i = 0; i < numerics.length; i++){ //process all numeric questions
        let tempQuestion = numerics[i].querySelector(".question").value; //process question
        let tempMin = numerics[i].querySelector(".minimum").value; //process min
        let tempMax = numerics[i].querySelector(".maximum").value; //process max
        let tempOrder = parseInt(numerics[i].id.replace("question_",''));
        surveyVar.questions.push(new NumericQuestion(tempQuestion,tempMin,tempMax,tempOrder,"numeric")); //Add question class to survey
    }
    console.log(JSON.stringify(surveyVar, null));
    return surveyVar; //return JSON of survey class
}

//assign event listeners to buttons
document.getElementById("new_question").addEventListener('click', newQuestion);
document.getElementById('rem_question').addEventListener('click', removeQuestion);
document.getElementById("survey_submit").addEventListener('click',surveySubmit);