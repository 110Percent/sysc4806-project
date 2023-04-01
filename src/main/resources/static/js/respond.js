$(document).ready(function () {
    let numQuestions = 0;
    let surveyId = 0;

    /**
     * construct html table for a written question JSON
     * @param num
     * @param question
     * @returns {string}
     */
    function writtenQuestion(num, question) {
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="survey-question WRITTEN">';
        questionHTML += '<tr> <td> <div class="question-query">' + num + '. ' + question.query + '</div></td></tr>';
        questionHTML += '<tr> <td> <textarea rows="4" cols="50" class="question-component round-input" placeholder="Response goes here..."></textarea> </td> </tr>';
        questionHTML += '</table>';
        return questionHTML;
    }

    /**
     * construct html table for a NUMERIC question JSON
     * @param num
     * @param question
     * @returns {string}
     */
    function numericQuestion(num, question) {
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="survey-question NUMERIC">';
        questionHTML += '<tr> <td> <div class="question-query">' + num + '. ' + question.query + ' (' + question.min + ' - ' + question.max + ') </div> </td></tr>';
        questionHTML += '<tr> <td> <input class="number question-component round-input" type="number" min="' + question.min + '" max="' + question.max + '"> </td> </tr>';
        questionHTML += '</table>';
        return questionHTML;

    }

    /**
     * construct html table for a multiselect question JSON
     * @param num
     * @param question
     * @returns {string}
     */
    function multiQuestion(num, question) {
        let questionHTML = '';
        questionHTML += '<table id="question_' + num + '" class="survey-question MULTISELECT">';
        questionHTML += '<tr> <td> <div class="question-query">' + num + '. ' + question.query + '</div></td></tr>';
        $.each(question.potentialAnswers, function (index, value) {
            questionHTML += '<tr> <td> <div class="question-component"><input type="radio" name="responseBody' + num + '" value="' + index + '"><label>' + value + '</label></div> </td></tr>';
        });
        questionHTML += '</table>';
        return questionHTML;
    }


    /**
     * create HTML for survey from its JSON
     * @param id
     */
    function loadSurvey(id) {
        numQuestions = 0;
        $.ajax({
            url: "/api/survey/noresponses?id=" + id,
            dataType: 'json',
            type: 'get',
            cache: 'false',
            success: function (survey) {
                let surveyHTML = '';
                if (survey.isClosed) {
                    $("main").empty();
                    $("main").append("<p> Survey closed </p>");
                    return;
                }
                $.each(survey.surveyQuestions, function (index, value) {
                    numQuestions++;
                    switch (value.questionType) {
                        case "WRITTEN":
                            surveyHTML += writtenQuestion(index + 1, value);
                            break;

                        case "MULTISELECT":
                            surveyHTML += multiQuestion(index + 1, value);
                            break;

                        case "NUMERIC":
                            surveyHTML += numericQuestion(index + 1, value);
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

    /**
     * grab the responses that are inputted and format them as JSON to be accepted by survey rest controller
     * @returns {string}
     */
    function constructResponse() {
        let data = '[';
        for (let i = 1; i <= numQuestions; i++) {
            let questionTable = $("#question_" + i)
            let responseType = questionTable.attr("class").split(' ')[1];
            switch (responseType) {
                case "WRITTEN":
                    if (!questionTable.find("textarea").val())
                        return "";
                    data += '{"responseType":"WRITTEN", "responseBody":"';
                    data += questionTable.find("textarea").val();
                    break;

                case "MULTISELECT":
                    if (!questionTable.find("input:checked").val())
                        return "";
                    data += '{"responseType":"MULTISELECT", "responseBody":"';
                    data += questionTable.find("input:checked").val();
                    break;

                case "NUMERIC":
                    if (!questionTable.find(".number").val())
                        return "";
                    data += '{"responseType":"NUMERIC", "responseBody":"';
                    data += questionTable.find(".number").val();
                    break;

                default:
                    console.log("TYPE ERROR");
            }
            if (i === numQuestions) {
                data += '"}';
            } else {
                data += '"},'
            }
        }
        data += ']';
        return data;
    }

    /**
     * submit survey when submit button is clicked
     * clear page and display if submission was successful
     */
    $("#submit_button").click(function () {
        let data = constructResponse();
        if (!data) {
            alert("Please complete survey");
            return;
        }
        $.ajax({
            contentType: 'application/json',
            type: "POST",
            url: '/api/survey/respond/' + surveyId,
            dataType: 'json',
            data: data,
            success: function () {
                $("main").empty();
                $("main").append("<p> Successfully submitted </p>");
                $("main").append("<a href='/'>Return To Dashboard</a>");
            },
            error: function () {
                $("main").empty();
                $("main").append("<p> Error Submitting </p>");
                $("main").append("<a href='/'>Return To Dashboard</a>");
            }
        });
    });

    const url = new URL($(location).attr("href"));
    let id = url.searchParams.get("id");
    loadSurvey(id);
});
