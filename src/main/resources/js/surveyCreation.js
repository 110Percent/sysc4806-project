/**
 * javascript file for generating survey creation structure
 * @author Ethan Houlahan, 101145675
 */

let questionCount = 0; //Number of questions within survey, used for generating id of question div


function newAnswer(event){
    let div = event.currentTarget.parentElement;
    div.appendChild(document.createElement("br"));
    let questionText = document.createElement("input");
    questionText.setAttribute("type", 'text');
    questionText.setAttribute("value",`Answer`);
    div.appendChild(questionText);

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

function generateQuestionId(){
    questionCount++;
    return "question_" + questionCount.toString();
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
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionText);

    }

    else if (questionType == 'numeric') {
        let questionDiv = document.createElement("div");
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
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
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMinValue);

        document.getElementById(`question_${questionCount.toString()}`).appendChild(document.createElement("br"));

        let questionMaxValueLabel = document.createElement("label");
        questionMaxValueLabel.innerText = "Maximum Value:";
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValueLabel);

        let questionMaxValue = document.createElement("input");
        questionMaxValue.setAttribute("type", 'number');
        questionMaxValue.setAttribute("name",`question_max_value_${questionCount.toString()}`);
        questionMaxValue.setAttribute("value","1");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionMaxValue);
    }

    else if (questionType == 'multiple') {
        let questionDiv = document.createElement("div");
        questionDiv.setAttribute("id", `question_${questionCount.toString()}`);
        questionDiv.setAttribute("style", `padding: 20px`);
        document.body.appendChild(questionDiv);

        let questionText = document.createElement("input");
        questionText.setAttribute("type", 'text');
        questionText.setAttribute("name",`question_text_${questionCount.toString()}`);
        questionText.setAttribute("value",`Question ${questionCount.toString()}`);
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


document.getElementById("new_question").addEventListener('click', newQuestion);
document.getElementById('rem_question').addEventListener('click', removeQuestion);