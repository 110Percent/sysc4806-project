/**
 * javascript file for generating survey creation structure
 * @author Ethan Houlahan, 101145675
 */

let questionCount = 0; //Number of questions within survey, used for generating id of question div

class Survey {
    constructor(name,questions){
        this.name = name;
        this.questions = questions;
    }
}
class Question {
    constructor(question, order,type) {
        this.question = question,
        this.order = order,
        this.questionType = type
    }
}
class MultiQuestion extends Question{
    constructor(question,answers,order,type) {
        super(question,order,type);
        this.answers = answers;
    }
}

class NumericQuestion extends Question{
    constructor(question,min,max, order,type) {
        super(question,order,type);
        this.min = parseInt(min);
        this.max = parseInt(max);

    }
}

function newAnswer(event){
    let div = event.currentTarget.parentElement;
    div.appendChild(document.createElement("br"));
    let questionAnswer = document.createElement("input");
    questionAnswer.setAttribute("type", 'text');
    questionAnswer.setAttribute("value",`Answer`);
    questionAnswer.classList.add("multipleAnswer");
    div.appendChild(questionAnswer);

}

function removeAnswer(event){
    let div = event.currentTarget.parentElement;
    if (div.lastChild.nodeName != "BUTTON"){

        if (div.lastChild.nodeName == "INPUT"){
            div.lastChild.remove();
        }
        if (div.lastChild.nodeName == "BR") {
            div.lastChild.remove();
        }
    }
}

function removeQuestion(){
    let lastQuestion = document.getElementById("question_" + (questionCount - 1).toString());
    try {
        lastQuestion.remove();
        questionCount--;
    }catch(err){
        alert("No Questions Found!")
    }

}

function newQuestion() {
    let questionType = $("select#question_type option:selected").val();
    if (questionType == 'text') {
        let questionDiv = document.createElement("div");
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.classList.add("text");
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        questionText.classList.add("question");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

    }

    else if (questionType == 'numeric') {
        let questionDiv = document.createElement("div");
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.setAttribute("style", `padding: 20px`);
        questionDiv.classList.add("numeric");
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.classList.add("question");
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let questionMinValueLabel = document.createElement("label");
        questionMinValueLabel.innerText = "Minimum Value:";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMinValueLabel);

        let questionMinValue = document.createElement("input");
        questionMinValue.setAttribute("type", 'number');
        questionMinValue.setAttribute("name",`question_min_value_${questionCount.toString()}`);
        questionMinValue.setAttribute("value","0")
        questionMinValue.classList.add("minimum");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMinValue);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let questionMaxValueLabel = document.createElement("label");
        questionMaxValueLabel.innerText = "Maximum Value:";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValueLabel);

        let questionMaxValue = document.createElement("input");
        questionMaxValue.setAttribute("type", 'number');
        questionMaxValue.setAttribute("name",`question_max_value_${questionCount.toString()}`);
        questionMaxValue.setAttribute("value","1");
        questionMaxValue.classList.add("maximum");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValue);
    }

    else if (questionType == 'multiple') {
        let questionDiv = document.createElement("div");
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.classList.add("multiple");
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        questionText.classList.add("question");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let newAnswerButton = document.createElement("button");
        newAnswerButton.setAttribute("id",`new_answer_question_${questionCount.toString()}`);
        newAnswerButton.addEventListener('click',newAnswer);
        newAnswerButton.innerText = "Add New Answer";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(newAnswerButton);

        let removeAnswerButton = document.createElement("button");
        removeAnswerButton.setAttribute("id",`rem_answer_question_${questionCount.toString()}`);
        removeAnswerButton.addEventListener('click',removeAnswer);
        removeAnswerButton.innerText = "Remove Answer";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(removeAnswerButton);
    }
    questionCount++;
}

function surveySubmit(){
    let surveyName = document.getElementById("survey_name").value;
    let surveyVar = new Survey(surveyName, []);
    let texts = document.querySelectorAll(".text");
    for (let i = 0; i < texts.length; i++){
        let tempQuestion = texts[i].querySelector(".question").value;
        let tempOrder = parseInt(texts[i].id.replace("question_",''));
        console.log(JSON.stringify(new Question(tempQuestion),null));
        surveyVar.questions.push(new Question(tempQuestion,tempOrder,"text"));
    }
    let multiples = document.querySelectorAll(".multiple");
    for (let i = 0; i < multiples.length; i++){
        let tempQuestion = multiples[i].querySelector(".question").value;
        const tempAnswers = [];
        let tempAnswersIterator = multiples[i].querySelectorAll(".multipleAnswer");
        let tempOrder = parseInt(multiples[i].id.replace("question_",''));
        for (let j = 0; j < tempAnswersIterator.length; j++){
            tempAnswers.push(tempAnswersIterator[j].value);
        }
        console.log(JSON.stringify(new MultiQuestion(tempQuestion,tempAnswers),null));
        surveyVar.questions.push(new MultiQuestion(tempQuestion,tempAnswers,tempOrder,"multiple"));
    }
    let numerics = document.querySelectorAll(".numeric");
    for (let i = 0; i < numerics.length; i++){
        let tempQuestion = numerics[i].querySelector(".question").value;
        let tempMin = numerics[i].querySelector(".minimum").value;
        let tempMax = numerics[i].querySelector(".maximum").value;
        let tempOrder = parseInt(numerics[i].id.replace("question_",''));
        surveyVar.questions.push(new NumericQuestion(tempQuestion,tempMin,tempMax,tempOrder,"numeric"));
    }
    console.log(JSON.stringify(surveyVar, null));
    return surveyVar;
}

document.getElementById("new_question").addEventListener('click', newQuestion);
document.getElementById('rem_question').addEventListener('click', removeQuestion);
document.getElementById("survey_submit").addEventListener('click',surveySubmit);