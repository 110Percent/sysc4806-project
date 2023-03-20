const url = `${window.location.pathname}/json`;
fetch(url)
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        const container = document.getElementById('container');
        container.innerHTML = '';
        let results = data;
        const titleDiv = document.createElement('div');
        let title = document.createElement('h1');
        title.id = 'survey_title';
        title.textContent = `${results.surveyTitle} Results`;
        titleDiv.appendChild(title);
        titleDiv.classList.add('row', 'flex-center');
        container.appendChild(titleDiv);

        let questionNum = 1;
        results.surveyQuestions.map(function (result) {
            const questionDiv = document.createElement('div');
            questionDiv.classList.add('result-question');
            let query = document.createElement('p');
            query.classList.add('question-query');
            query.textContent = `${questionNum}. ${result.query}`;
            questionNum++;
            questionDiv.appendChild(query);
            let questionType = `${result.questionType}`;

            if (questionType === 'NUMERIC') {
                const canvas = document.createElement('canvas');
                canvas.classList.add("canvas")
                questionDiv.appendChild(canvas);

                const histCtx = canvas.getContext('2d');
                new Chart(histCtx, {
                    type: 'bar', data: {
                        labels: result.answers, datasets: [{
                            label: `${result.query}`,
                            data: result.results,
                            backgroundColor: 'rgba(255, 99, 132, 0.8)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                    }, options: {
                        scales: {
                            x: {
                                grid: {
                                    display: false
                                }
                            }, y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }
            if (questionType === 'MULTISELECT') {
                const canvas = document.createElement('canvas');
                canvas.classList.add("canvas")
                questionDiv.appendChild(canvas);

                const pieCtx = canvas.getContext('2d');
                new Chart(pieCtx, {
                    type: 'pie', data: {
                        labels: result.answers, datasets: [{
                            label: `${result.query}`,
                            data: result.results,
                            backgroundColor: ['rgba(255, 99, 132, 0.8)', 'rgba(54, 162, 235, 0.8)', 'rgba(255, 206, 86, 0.8)', 'rgba(75, 192, 192, 0.8)', 'rgba(153, 102, 255, 0.8)', 'rgba(255, 159, 64, 0.8)']
                        }]
                    }
                });
            }
            if (questionType === 'WRITTEN') {
                const div = document.createElement('div');
                div.classList.add("list")
                questionDiv.appendChild(div);

                for (let i = 0; i < result.results.length; i++) {
                    const li = document.createElement("li");
                    li.appendChild(document.createTextNode(result.results[i]));
                    li.classList.add("question-component")
                    div.appendChild(li);
                }
            }
            container.appendChild(questionDiv);
        });
    })
    .catch(function (error) {
        console.log(error);
    });
