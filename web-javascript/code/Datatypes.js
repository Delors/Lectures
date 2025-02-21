console.log("Undefined ------------------------------------------------------");
let u = undefined;
console.log("u", u);

console.log("Number ---------------------------------------------------------");
let i = 1; // double-precision 64-bit binary IEEE 754 value
let f = 1.0; // double-precision 64-bit binary IEEE 754 value
let l = 10_000;
let binary = 0b1010;
console.log("0b1010", binary);
let octal = 0o12;
console.log("0o12", octal );
let hex = 0xA;
console.log("0xA", hex);
console.log(
  Number.MIN_VALUE,
  Number.MIN_SAFE_INTEGER,
  Number.MAX_SAFE_INTEGER,
  Number.MAX_VALUE,
);
let x = NaN;
let y = Infinity;
let z = -Infinity;

// Standard Operatoren: +, - , *, /, %, ++, --, **
// Bitwise Operatoren: &, |, ^, ~, <<, >>, >>> 
//                     (operieren immer auf dem Ganzzahlwert der Bits)
console.log("i =", i, "; i++  ", i++); // 1 oder 2?
console.log("i =", i, "; ++i ", ++i); // 2 oder 3?
console.log("2 ** 4 === 0 ", 2 ** 4);
console.log("7 % 3 === ", 7 % 3);
console.log("1 / 0 === ", 1 / 0);


console.log("BigInt ---------------------------------------------------------");
let ib = 1n; 
console.log(100n === BigInt(100));
console.log(Number.MAX_SAFE_INTEGER + 2102); // 9007199254743092
console.log(BigInt(Number.MAX_SAFE_INTEGER) + 2102n); // 9007199254743093n


console.log("Boolean --------------------------------------------------------");
let b = true; // oder false 
console.log("Boolean(undefined)", Boolean(undefined)); // true oder false?
console.log(null == true ? "true" : "false"); // true oder false?


console.log("(Quasi-)Logische Operatoren ------------------------------------");
console.log('1 && "1": ', 1 && "1");
console.log('null && "1": ', null && "1");
console.log("null && true: ", null && true);
console.log("true && null: ", true && null);
console.log("null && false: ", null && false);
console.log("{} && true: ", {} && true);

// Neben den Standardoperatoren: ``&&``, ``||``, ``!`` gibt es auch noch ``??``
// Der ``??``-Operator gibt den rechten Operanden zurück, wenn der linke Operand
// ``null`` oder ``undefined`` ist. Andernfalls gibt er den linken Operanden 
// zurück.
// ``??`` ist der *nullish coalescing operator (??) (vergleichbar zu ||)*
console.log('1 ?? "1": ', 1 ?? "1");
console.log('null ?? "1": ', null ?? "1");
console.log("null ?? true: ", null ?? true);
console.log("true ?? null: ", true ?? null);
console.log("null ?? false: ", null ?? false);
console.log("{} ?? true: ", {} ?? true);

console.log('undefined ?? "1": ', undefined ?? "1");
console.log('undefined ?? "1": ', undefined ?? "1");
console.log("undefined ?? true: ", undefined ?? true);
console.log("true ?? undefined: ", true ?? undefined);
console.log("undefined ?? false: ", undefined ?? false);
console.log("undefined ?? undefined: ", undefined ?? undefined);


console.log("Strings --------------------------------------------------------");
let _s = "42";
console.log("Die Antwort ist " + _s + "."); // String concatenation
console.log(`Die Antwort ist ${_s}.`); // Template literals (Template strings)
// multiline Strings
console.log(` 
    Die Antwort mag ${_s} sein,
    aber was ist die Frage?`);

console.log(String(42)); // "42"


console.log("Objekte --------------------------------------------------------");
let emptyObject = null;
let anonymousObj = {
  i: 1,
  u: { j: 2, v: { k: 3 } },
  toString: function () {
    return "anonymousObj";
  },
  "?" : "question mark"
};
// Zugriff auf die Eigenschaften eines Objekts
anonymousObj.j = 2; // mittels Bezeichner ("j") (eng. Identifier)
anonymousObj["j"] = 4; // mittels String ("j")
anonymousObj["k"] = 3;
console.log("anonymousObj:                     ", anonymousObj);
console.log("anonymousObj.toString():          ", anonymousObj.toString());
delete anonymousObj["?"]; // delete dient dem Löschen von Eigenschaften
delete anonymousObj.toString; // delete dient dem Löschen von Eigenschaften
console.log("anonymousObj.toString() [original]", anonymousObj.toString());
// Der Chain-Operator kann verwendet werden, um auf Eigenschaften (Properties) 
// von Objekten zuzugreifen, ohne dass eine Fehlermeldung ausgegeben wird, 
// wenn eine (höher-liegende) Eigenschaft nicht definiert ist.
// Besonders nützlich beim Verarbeiten von komplexen JSON-Daten.
console.log("anonymousObj.u?.v.k", anonymousObj.u?.v.k); 
console.log("anonymousObj.u.v?.k", anonymousObj.u.v?.k);
console.log("anonymousObj.u.v?.z", anonymousObj.u.v?.z);
console.log("anonymousObj.u.q?.k", anonymousObj.u.q?.k);
console.log("anonymousObj.p?.v.k", anonymousObj.p?.v.k);

// Nützliche Zuweisungen, um den Fall undefined und null gemeinsam zu behandeln:
anonymousObj.name ||= "Max Mustermann";



console.log("Date -----------------------------------------------------------");
let date = new Date("8.6.2024"); // ACHTUNG: Locale-Settings
console.log(date);


console.log("Funktionen sind auch Objekte -----------------------------------");
let func = function () {
  return "Hello World";
};
console.log(func, func());


console.log("Arrays ---------------------------------------------------------");
let temp = undefined;
let $a = [1];
console.log("let $a = [1]; $a, $a.length", $a, $a.length);
$a.push(2); // append
console.log("$a.push(2); $a", $a);
temp = $a.unshift(0); // "prepend" -> return new length
console.log("temp = $a.unshift(0); temp, $a", temp, $a);
temp = $a.shift(); // remove first element -> return removed element
console.log("temp = $a.shift(); temp, $a", temp, $a);
// Um zu prüfen ob eine Datenstruktur ein Array ist:
console.log("Array.isArray($a)", Array.isArray($a));
console.log("Array.isArray({})", Array.isArray({}));
console.log("Array.isArray(1)", Array.isArray(1));


console.log("Symbols --------------------------------------------------------");
let sym1 = Symbol("1"); // a unique and immutable primitive value
let sym2 = Symbol("1");
let obj1Values = { sym1: "value1", sym2: "value2" };
console.log(obj1Values);
console.log(`sym1 in ${JSON.stringify(obj1Values)}: `, sym1 in obj1Values);
let obj2Values = { [sym1]: "value1", [sym2]: "value2" };
console.log(obj2Values);
console.log(`sym1 in ${JSON.stringify(obj2Values)}: `, sym1 in obj2Values);
console.log(obj1Values, " vs. ", obj2Values);

console.log( { sym1 : "this", sym1 : "that"  }); // ??? { sym1: "that" }
console.log("sym1 == sym2", sym1 == sym2); 