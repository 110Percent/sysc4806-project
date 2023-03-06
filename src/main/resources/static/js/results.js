const ul = document.getElementById('rslts');
const url = `${window.location.pathname}/json`;
const list = document.createDocumentFragment();

fetch(url)
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    let results = data;
    console.log(results);

    results.surveyQuestions.map(function(result) {
        let li = document.createElement('li');
        let query = document.createElement('h2');

        query.innerHTML = `${result.query}`;

        let question_list = document.createDocumentFragment();
        let qli = document.createElement('li');
        qli.appendChild(query);
        question_list.appendChild(qli);
        result.responses.map(function(response) {
            let rli = document.createElement('li');
            rli.innerHTML = `${response.responseBody}`;
            question_list.appendChild(rli);
        })

        li.appendChild(question_list);
        list.appendChild(li);
    });
    ul.appendChild(list);
  })
  .catch(function(error) {
    console.log(error);
  });
