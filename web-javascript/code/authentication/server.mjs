import express from "express";
import fs from "fs";
import path from "node:path";
import { fileURLToPath } from "url";
import jwt from "jsonwebtoken";
import crypto from "crypto";
import bodyParser from "body-parser";

const app = express();

const SERVER_SECRET = crypto.randomBytes(64).toString("hex");
const users = JSON.parse(
  fs.readFileSync(
    path.resolve(path.dirname(fileURLToPath(import.meta.url)), "users.json"),
    "utf8",
  ),
);
console.log("Users: " + JSON.stringify(users));

app.use(express.static("."));
app.use(express.json());
app.use(bodyParser.text());

const verifyToken = (req, res, next) => {
  console.log("Headers: " + JSON.stringify(req.headers));

  const token = req.headers["authorization"].split(" ")[1];
  if (!token) {
    return res.status(401).json({ error: "Unauthorized" });
  }

  jwt.verify(token, SERVER_SECRET, (err, decoded) => {
    console.log("Decoded: " + JSON.stringify(decoded));
    if (err) {
      return res.status(401).json({ error: "Unauthorized" });
    }
    req.userIndex = decoded.userIndex;
    next();
  });
};

app.get("/admin/login", function (req, res) {
  const name = req.query.name;
  const password = req.query.password; // in a real app use hashed passwords!

  if (!name || !password) {
    res.status(400).send("Missing name or password");
    return;
  }

  let userIndex = -1;
  for (let i = 0; i < users.length; i++) {
    if (users[i].name === name && users[i].password === password) {
      userIndex = i;
      break;
    }
  }
  if (userIndex === -1) {
    res.status(401).send("Credentials invalid.");
    return;
  }
  console.log(
    "Authenticated: " + users[userIndex].name + " " + users[userIndex].password,
  );

  // Here, we can use the userIndex to identify the user;
  // but his only works as long as the user list is fixed.
  // In a real app use, e.g., a users's email.
  const token = jwt.sign({ userIndex: userIndex }, SERVER_SECRET, {
    expiresIn: "2h",
  });
  res.status(200).json({ token });
});

app.post("/admin/question", verifyToken, function (req, res) {
  const userIndex = req.userIndex;
  const question = req.body;
  const m = `The user: ${users[userIndex].name} asked: ${question} `;
  console.log(m);

  res.status(200).send("Question stored. Preliminary answer: 42.");
});

// Attention: a port like 6666 will not work on (most?) browsers
const port = 8080;
var server = app.listen(port, function () {
  console.log(`Running at http://127.0.0.1:${port}/`);
});
