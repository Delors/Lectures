const express = require('express');
const app = express();
const expressWs = require('express-ws')(app);

app.use(express.static('.'));

app.get('/', function (_, res) {
    res.end("Connect to /time to get the current time.");
})

app.ws('/time', function(ws, req) {
    setInterval(function() {ws.send(new Date().toLocaleTimeString());}, 50);
});

var server = app.listen(8888, function () {
    console.log("Express App running at http://127.0.0.1:8888/");
})
