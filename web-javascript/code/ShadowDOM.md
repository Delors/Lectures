# Demonstrates the encapsulation of styles and DOM structure using Shadow DOM.

```html
<div>
  <div id="host"></div>
</div>
<div>
  <div class="a-host">
    <template shadowrootmode="open">
      <span>I'm in the 2nd shadow DOM</span>
    </template>
  </div>
</div>
<span>I'm not in the shadow DOM</span>

```

```css
:root {
  font-size: 2em;
}
body {
  margin: 5px;
}

span {
  color: blue;
  border: 1px solid black;
}
div.a-host {
  color: green;
}

```

```js
const host = document.querySelector("#host");
const shadow = host.attachShadow({ mode: "open" });
const span = document.createElement("span");
span.textContent = "I'm in the shadow DOM";
shadow.appendChild(span);

```