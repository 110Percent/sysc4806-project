/**
 * javascript file for generating survey creation structure
 * @author Ethan Houlahan, 101145675
 */

let questionCount = 0; //Number of questions within survey, used for generating id of question div


function newAnswer(event){
    let div = event.currentTarget.parentElement;
    alert(div.id);
}

function removeAnswer(event){
    let div = event.currentTarget.parentElement;
    alert(div.id);
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
    console.log("CLICKS 1")
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

        let questionBreak = document.createElement("br");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionBreak);

        let questionValue = document.createElement("input");
        questionValue.setAttribute("type", 'text');
        questionValue.setAttribute("name",`question_value_${questionCount.toString()}`);
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionValue);
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

        let questionBreak = document.createElement("br");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionBreak);

        let questionValue = document.createElement("input");
        questionValue.setAttribute("type", 'number');
        questionValue.setAttribute("name",`question_value_${questionCount.toString()}`);
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionValue);
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

        let questionBreak = document.createElement("br");
        document.getElementById(`question_${questionCount.toString()}`).appendChild(questionBreak);

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