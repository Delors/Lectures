/*
Initializes the login interface.
*/
document
  .getElementsByTagName("main")[0]
  .replaceChildren(document.getElementById("log-in").content.cloneNode(true));
document.getElementById("login-dialog").showModal();
document.getElementById("login-button").addEventListener("click", login);

let jwt = null; // JSON Web Token for authentication

async function login() {
  const name = document.getElementById("administrator").value;
  const password = document.getElementById("password").value;
  const urlEncodedName = encodeURIComponent(name);
  const urlEncodedPassword = encodeURIComponent(password);
  const response = await fetch(
    "http://" +
      location.host +
      "/admin/login?name=" +
      urlEncodedName +
      "&password=" +
      urlEncodedPassword,
  );
  if (response.status !== 200) {
    console.error("Login failed: " + response.status);
    return;
  }
  const responseJSON = await response.json();
  jwt = responseJSON.token;
  console.log("Received JWT: " + jwt);

  const d = document;
  d.getElementById("login-dialog").close();

  d
    .getElementsByTagName("main")[0]
    .replaceChildren(d.getElementById("logged-in").content.cloneNode(true));
  d.getElementById("enter-question-dialog").showModal();
  d.getElementById("send-question").addEventListener("click", sendQuestion);
}

async function sendQuestion() {
  const question = document.getElementById("question").value;

  const response = await fetch("http://" + location.host + "/admin/question", {
    method: "POST",
    headers: {
      "Content-Type": "text/plain",
      Authorization: `Bearer ${jwt}`,
    },
    body: question,
  });
  const text = await response.text();
  showAnswer(text);
}

function showAnswer(text) {
  document.getElementById("answer-dialog").showModal(false);
  document.getElementById("answer-paragraph").textContent = text;
}
