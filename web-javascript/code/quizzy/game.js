const express = require('express');
const app = express();

const expressWs = require('express-ws')(app);

let clients = 0;
let playerWSs = [];

let adminWS = null;
let answersCount = 0;
let correctAnswersCount = 0;

app.use(express.static('.')); // required to serve static files


function sendCurrentPlayers() {
    if (adminWS && playerWSs.length > 0) {
        allPlayers = playerWSs
            .filter(player => player.name)
            .map(player => { return { "id": player.id, "name": player.name } })
        console.log("Sending current players: " + JSON.stringify(allPlayers));
        const m = JSON.stringify({ "type": "players", "players": allPlayers });
        adminWS.send(m);
    }
}

function sendNextQuestion() {
    answersCount = 0;
    correctAnswersCount = 0;
    const question = "What is the capital of France?";
    const answers = ["Paris", "London", "Berlin", "Madrid"];
    const correct = "Paris";

    const nextQuestion = JSON.stringify({
        "type": "question",
        "question": question,
        "answers": ["Paris", "London", "Berlin", "Madrid"]
    })
    playerWSs.forEach(player => player.ws.send(nextQuestion));
    adminWS.send(JSON.stringify({
        "type": "question",
        "question": question,
        "answers": answers,
        "correct": correct
    }));
}

function sendResults() {
    const results = playerWSs.map(player => {
        return { "id": player.id, "name": player.name, "wins": player.wins }
    });
    const sortedResults = results.sort((a, b) => b.wins - a.wins);
    const resultsMsg = JSON.stringify({
        "type": "results",
        "results": sortedResults
    });
    playerWSs.forEach(player => player.ws.send(resultsMsg));
    adminWS.send(resultsMsg);

}


function handleAnswer(clientId, answer) {
    const correct = answer.answer === "Paris";
    const player = playerWSs.find(player => player.id === clientId);
    if (correct) {
        if (correctAnswersCount === 0) {
            player.wins++;
        }
        correctAnswersCount++;
    }
    answersCount++;
    if (answersCount === playerWSs.length) {
        // sendNextQuestion();
        sendResults();
    } else {
        adminWS.send(JSON.stringify({
            "type": "answers",
            "count": answersCount,
            "correctAnswersCount": correctAnswersCount
        }));
    }
}


app.ws('/player', function (ws, request) {
    const clientId = clients++;
    const playerData = { "ws": ws, "id": clientId, "wins": 0 }
    playerWSs.push(playerData);
    ws.onmessage = function (event) {
        message = JSON.parse(event.data);
        switch (message.type) {
            case "registration":
                const name = message.name;
                console.log("Registration: " + clientId + "/" + name);
                playerData.name = name;
                sendCurrentPlayers();
                break;

            case "answer":
                const answer = message;
                handleAnswer(clientId, answer);
                break;

            default:
                console.log("Unknown message: " + message);
                break;
        }
    };
    ws.onclose = function () {
        console.log("Player disconnected: " + clientId);
        playerWSs = playerWSs.filter(player => player.id !== clientId);
        sendCurrentPlayers();
    };
    ws.onerror = function () {
        console.log("Player error: " + clientId);
        playerWSs = playerWSs.filter(player => player.id !== clientId);
        sendCurrentPlayers();
    };
});

app.ws('/admin', function (ws, req) {
    adminWS = ws;
    sendCurrentPlayers(); // on admin registration send current players
    ws.onmessage = function (event) {
        message = JSON.parse(event.data);
        switch (message.type) {
            case "start":
                console.log("Start game");
                sendNextQuestion();
                break;
            default:
                console.log("Unknown message: " + message);
                break;
        }
    };

    ws.onclose = (event) => {
        console.log("Admin disconnected");
        adminWS = null;
        sendCurrentPlayers();
    };

    ws.onerror = (event) => {
        console.log("Admin error: " + event);
        sendCurrentPlayers();
    };

});


const PORT = process.env.QUIZZY_PORT || 8800;

var server = app.listen(PORT, function () {
    console.log(`Quizzy running at http://127.0.0.1:${PORT}/`);
})
