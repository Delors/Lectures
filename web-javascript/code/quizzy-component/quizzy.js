/**
 * A small web component which enable us to integrate a small quiz
 * into a webpage.
 */
const quizzyStyles = new CSSStyleSheet();
quizzyStyles.replaceSync(`
:host {
    display: block;
    width: 100%;
    height: 10lh;
    background-color: #f0f0f0
}
main {
    height: 100%;

    .select-mode {
        height: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 1em;
        align-content: space-around;
        justify-content: space-around;

        button,
        button[type="button"] {
            width: 40%;
            height: 2.5lh;
            flex-grow: 0;
            padding: 0.5em;
            font-size: inherit;
        }
    }
}
`);

class Quizzy extends HTMLElement {
    constructor() {
        super();

        const shadow = this.attachShadow({ mode: "open" });

        shadow.adoptedStyleSheets = [quizzyStyles];
        shadow.innerHTML = `
            <main>
              <div class="select-mode">
                <button id="clientButton">Join Quizzy</button>
                <button id="adminButton">Administrate Quizzy</button>
              </div>
            </main>`;
    }

    connectedCallback() {
        const wsURL = this.getAttribute("ws-url");

        /**
         * Client logic
         */
        this.shadowRoot
            .getElementById("clientButton")
            .addEventListener("click", () => {
                this.shadowRoot.innerHTML = `
                <main>
                  <form>
                    <input type="text" id="username" placeholder="Username">
                    <button id="submit" type="button">Submit</button>
                  </form>
                </main>`;

                const ws = new WebSocket(`${wsURL}/player`);

                ws.onmessage = (event) => {
                    const data = JSON.parse(event.data);
                    switch (data.type) {
                        case "question":
                            showQuestion(data);
                            break;
                        case "results":
                            showResults(data);
                            break;
                        default:
                            console.warn("unknown message", data);
                            break;
                    }
                };
                ws.onclose = (event) => {
                    console.log("connection closed", event);
                };
                ws.onerror = (event) => {
                    console.error("fatal error", event);
                };

                const showResults = (data) => {
                    console.log("show results", data);
                    const main = this.shadowRoot.querySelector("main");
                    main.innerHTML = `<h1>Results</h1>`;
                    for (const result of data.results) {
                        main.innerHTML += `<p>${result.name}: ${result.wins}</p>`;
                    }
                };

                const showQuestion = (data) => {
                    const main = this.shadowRoot.querySelector("main");
                    main.innerHTML = `<h1>Question</h1><p>${data.question}</p>`;

                    const createAnswerButton = (answer) => {
                        const button = document.createElement("button");
                        button.innerText = answer;
                        button.onclick = submitAnswer(answer);
                        return button;
                    };

                    for (const answer of data.answers) {
                        main.appendChild(createAnswerButton(answer));
                    }
                };

                const submitAnswer = (answer) => {
                    return () => {
                        ws.send(
                            JSON.stringify({
                                type: "answer",
                                answer: answer,
                            }),
                        );
                        doWait();
                    };
                };

                setTimeout(() => {
                    this.shadowRoot
                        .getElementById("submit")
                        .addEventListener("click", () => {
                            submitUsername();
                        });
                });

                const submitUsername = () => {
                    const name =
                        this.shadowRoot.getElementById("username").value;
                    ws.send(
                        JSON.stringify({
                            type: "registration",
                            name: name,
                        }),
                    );

                    doWait();
                };

                const doWait = () => {
                    const main = this.shadowRoot.querySelector("main");
                    main.innerHTML = "Waiting for other players...";
                };
            });

        /**
         * Admin logic
         */
        this.shadowRoot
            .getElementById("adminButton")
            .addEventListener("click", () => {
                this.shadowRoot.innerHTML = `
                <main>
                    <h1>Players</h1>
                    <p id="players"></p>
                    <button id="startGame" type="button">Start Game</button>
                </main>`;

                const ws = (this.ws = new WebSocket(`${wsURL}/admin`));

                ws.onmessage = (event) => {
                    const data = JSON.parse(event.data);
                    console.log("Received: " + event.data);
                    switch (data.type) {
                        case "players":
                            const players =
                                this.shadowRoot.getElementById("players");
                            players.innerText =
                                "[" +
                                data.players.length +
                                " players] " +
                                data.players
                                    .map(
                                        (player) =>
                                            player.id + ": " + player.name,
                                    )
                                    .join(", ");
                            break;
                        case "question":
                            this.shadowRoot.querySelector("main").innerText = `
                            question: ${data.question}; correct answer: ${data.correct}
                        `;
                            break;
                        case "results":
                            const main = this.shadowRoot.querySelector("main");
                            main.innerText = "Result: " + event.data;
                            break;
                        default:
                            console.log("unknown: " + event.data);
                            break;
                    }
                };

                ws.onclose = (event) => {
                    console.log("Connection closed: " + event);
                    const main = this.shadowRoot.querySelector("main");
                    main.innerText = "Connection closed - you need to restart.";
                };
                ws.onerror = (event) => {
                    console.log("Connection error", event);
                };

                function startGame() {
                    ws.send(JSON.stringify({ type: "start" }));
                }

                setTimeout(() => {
                    this.shadowRoot
                        .getElementById("startGame")
                        .addEventListener("click", () => {
                            startGame();
                        });
                });
            });

        console.log("Quizzy component connected", this.wsURL);
    }

    disconnectedCallback() {
        console.log("Quizzy component disconnected");
        // Clean up any resources, event listeners, or connections,.... TODO
    }
}

customElements.define("ld-quizzy", Quizzy);
