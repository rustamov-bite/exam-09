answerBtn();

function answerBtn() {
    const answerBtn = document.getElementsByClassName('answer-btn')[0];

    answerBtn.addEventListener('click', function (event) {
        event.preventDefault();



        document.getElementsByClassName('answer-btn-div')[0].style.display = 'none';
        const userAnswerEl = document.getElementsByClassName('form-div')[0];
        userAnswerEl.style.display = 'block';
        sendAnswer();
    });
}

function sendAnswer() {
    const answerForm = document.getElementsByClassName('user-answer-form')[0];

    answerForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(form);
        send(data);
    });
}

function drawAnswer(answer) {
    const tmp = document.createElement('div');
    tmp.classList.add('d-flex', 'flex-column')
    tmp.innerHTML = `<div class="topic d-flex flex-row">
                            <div>
                                <p>${answer.user.name}</p>
                            </div>
                            <div>
                                <p>${answer.answerDate}</p>
                            </div>
                        </div>
                        <div>
                            <p>${answer.message}</p>
                        </div>`;
    document.getElementsByClassName('answers')[0].append(tmp);
}

async function send(data) {
    await fetch('http://localhost:8989/answers/add', {
        method: 'POST',
        body: data
    }).then(res => res.json()).then(res => drawAnswer(res))
}