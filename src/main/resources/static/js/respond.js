$(document).ready(function(){
    let numQuestions = 0;
    let surveyId = 0;
    function writtenQuestion(num, question){
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="WRITTEN">';
        questionHTML += '<caption> Question ' + num + '</caption>';
        questionHTML += '<tr> <td>' + question.query + '</td></tr>';
        questionHTML += '<tr> <td> <textarea rows="4" cols="50">Response goes here...</textarea> </td> </tr>';
        questionHTML += '</table>';
        return questionHTML;
    }

    function numericQuestion(num, question){
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="NUMERIC">';
        questionHTML += '<caption> Question ' + num + '</caption>';
        questionHTML += '<tr> <td>' + question.query + ' (' + question.min + ' - ' + question.max + ') </td></tr>';
        questionHTML += '<tr> <td> <input class="number" type="number" min="' + question.min + '" max="' + question.max +'"> </td> </tr>';
        questionHTML += '</table>';
        return questionHTML;

    }

    function multiQuestion(num, question){
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="MULTISELECT">';
        questionHTML += '<caption> Question ' + num + '</caption>';
        questionHTML += '<tr> <td>' + question.query + '</td></tr>';
        $.each(question.answers, function(index, value){
            questionHTML += '<tr> <td> <input type="radio" name="responseBody" value="' + index + '"><label>' + value + '</label> </td></tr>';
        });
        questionHTML += '</table>';
        return questionHTML;

    }

    function loadSurvey(id){
        numQuestions = 0;
        $.ajax({
            url: "/api/survey/noresponses?id=" + id,
            dataType: 'json',
            type: 'get',
            cache: 'false',
            success: function(survey){
                let surveyHTML ='';
                $.each(survey.surveyQuestions, function (index, value){
                    numQuestions++;
                    switch (value.questionType){
                        case "WRITTEN":
                            surveyHTML += writtenQuestion(index+1, value);
                            break;

                        case "MULTISELECT":
                            surveyHTML += multiQuestion(index+1, value);
                            break;

                        case "NUMERIC":
                            surveyHTML += numericQuestion(index+1, value);
                            break;

                        default:
                            console.log("TYPE ERROR");
                    }
                });
                $("#survey_title").empty();
                $("#survey_body").empty();
                $("#survey_title").append(survey.surveyTitle);
                $("#survey_body").append(surveyHTML);
                surveyId = survey.id;
            }
        });
    }

    function constructResponse(){
        let data = '[';
        for(let i = 1; i <= numQuestions; i++){
            let questionTable = $("#question_" + i)
            let responseType = questionTable.attr("class");
            switch (responseType){
                case "WRITTEN":
                    data+= '{"responseType":"WRITTEN", "responseBody":"';
                    data+= questionTable.find("textarea").val();
                    break;

                case "MULTISELECT":
                    data+= '{"responseType":"MULTISELECT", "responseBody":"';
                    data+= questionTable.find("input:checked").val();
                    break;

                case "NUMERIC":
                    data+= '{"responseType":"NUMERIC", "responseBody":"';
                    data+= questionTable.find(".number").val();
                    break;

                default:
                    console.log("TYPE ERROR");
            }
            if(i === numQuestions){
                data+= '"}';
            }
            else{
                data+= '"},'
            }
        }
        data += ']';
        return data;
    }

    $("#submit_button").click(function(){
        $.ajax
        ({
            contentType: 'application/json',
            type: "POST",
            url: '/api/survey/respond/' + surveyId,
            dataType: 'json',
            data: constructResponse(),
            success: function(){
                $("main").empty();
                $("main").append("<p> Successfully submitted </p>");
            },
            error: function (){
                $("main").empty();
                $("main").append("<p> Error Submitting </p>");
            }
        });
    });

    const url = new URL($(location).attr("href"));
    let id = url.searchParams.get("id");
    loadSurvey(id);
});