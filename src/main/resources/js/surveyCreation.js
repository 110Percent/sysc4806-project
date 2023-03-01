
let questionCount = 0;
function newAnswer(event){
    let div = event.target.parent;
    alert(div.id());
}

function removeAnswer(event){
    let div = event.target.parent;
    alert(div.id());
}

function generateQuestionId(){
    questionCount++;
    return "question_" + questionCount.toString();
}

function removeQuestion(){
    let lastQuestion = document.getElementById("question_" + questionCount.toString());
    lastQuestion.remove();
    questionCount--;
}

function newQuestion() {
    let questionType = $("select#question_type option:selected").val();

    if (questionType == 'text') {
        document.body.innerHTML +=
            `<div id = '${generateQuestionId()}' style = 'padding: 20px'> ` +
            "<input type = 'text' value = 'Question'> <br> " +
            "<input type = 'text'>"
    }
    else if (questionType == 'numeric') {
        document.body.innerHTML +=
            `<div id = '${generateQuestionId()}' style = 'padding: 20px'>`  +
            "<input type = 'text' value = 'Question'> <br> " +
            "<input type = 'number'>"
    }
    else if (questionType == 'multiple') {
        document.body.innerHTML +=
            `<div id = '${generateQuestionId()}' style = 'padding: 20px'> ` +
            "<input type = 'text' value = 'Question'> <br> " +
            "<input> <button onclick= 'newAnswer'>Add Option</button> " +
            "<button onclick = 'removeAnswer'>Remove Answer</button> </div>"
    }
}