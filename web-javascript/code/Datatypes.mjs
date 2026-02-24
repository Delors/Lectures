import { log, done } from "./log.mjs"; 

log("\nUndefined ----------------------------------------------------");
let u = undefined;
log("u", u);

log("\nNumber -------------------------------------------------------");
let i = 1; // double-precision 64-bit binary IEEE 754 value
let f = 1.0; // double-precision 64-bit binary IEEE 754 value
let l = 10_000;
let binary = 0b1010;
log("0b1010", binary);
let octal = 0o12;
log("0o12", octal);
let hex = 0xa;
log("0xA", hex);
log(
    Number.MIN_VALUE,
    Number.MIN_SAFE_INTEGER,
    Number.MAX_SAFE_INTEGER,
    Number.MAX_VALUE,
);
let x = NaN;
let y = Infinity;
let z = -Infinity;

// Standard Operatoren: +, - , *, /, %, ++, --, **
// Bitwise Operatoren:  &, |, ^, ~, <<, >>, >>>
//                      werden immer auf dem Ganzzahlwert der Bits angewendet:
//                      Beispiel: 1234.5678e4 << 1 = 24691356
log("i =", i, "; i++ ", i++); // 1 oder 2?
log("i =", i, "; ++i ", ++i); // 2 oder 3?
log("2 ** 4 === 0 ", 2 ** 4);
log("7 % 3 === ", 7 % 3);
log("1 / 0 === ", 1 / 0);

log("\nBigInt -------------------------------------------------------");
let ib = 1n;
log(100n === BigInt(100));
log(Number.MAX_SAFE_INTEGER + 2102); // 9007199254743092
log(BigInt(Number.MAX_SAFE_INTEGER) + 2102n); // 9007199254743093n

log("\nBoolean ------------------------------------------------------");
let b = true; // oder false
log("Boolean(undefined)", Boolean(undefined)); // true oder false?
log(null == true ? "true" : "false"); // true oder false?

log("\n(Quasi-)Logische Operatoren ----------------------------------");
log('1 && "1": ', 1 && "1");
log('null && "1": ', null && "1");
log("null && true: ", null && true);
log("true && null: ", true && null);
log("null && false: ", null && false);
log("{} && true: ", {} && true);

// Neben den Standardoperatoren: ``&&``, ``||``, ``!`` gibt es auch noch ``??``
// Der ``??``-Operator gibt den rechten Operanden zurück, wenn der linke Operand
// ``null`` oder ``undefined`` ist. Andernfalls gibt er den linken Operanden
// zurück.
// ``??`` ist der *nullish coalescing operator (??)*
// Falls der linke Wert null oder undefined ist, dann ist er vergleichbar zu ||
log('1 ?? "1": ', 1 ?? "1");
log('null ?? "1": ', null ?? "1");
log("null ?? true: ", null ?? true);
log("true ?? null: ", true ?? null);
log("null ?? false: ", null ?? false);
log("{} ?? true: ", {} ?? true);

log('undefined ?? "1": ', undefined ?? "1");
log("undefined ?? true: ", undefined ?? true);
log("true ?? undefined: ", true ?? undefined);
log("undefined ?? false: ", undefined ?? false);
log("undefined ?? undefined: ", undefined ?? undefined);

log("\nStrings ------------------------------------------------------");
let _s = "42";
log("Die Antwort ist " + _s + "."); // String concatenation
log(`Die Antwort ist ${_s}.`); // Template literals (Template strings)
// multiline Strings
log(`
    Die Antwort mag ${_s} sein,
    aber was ist die Frage?`);

log(String(42)); // "42"

// ACHTUNG Objekte und Errors am Besten direkt an log übergeben,
// damit die Objekteigenschaften ausgeben werden.
log("State: " + { a: "abc" }, { a: "abc" });

log("\nObjekte ------------------------------------------------------");
let emptyObject = null;
let anonymousObj = {
    i: 1,
    u: { j: 2, v: { k: 3 } },
    toString: function () {
        return "anonymousObj";
    },
    "?": "question mark",
};
// Zugriff auf die Eigenschaften eines Objekts
anonymousObj.j = 2; // mittels Bezeichner ("j") (eng. Identifier)
anonymousObj["j"] = 4; // mittels String ("j")
anonymousObj["k"] = 3;
log("anonymousObj:                     ", anonymousObj);
log("anonymousObj.toString():          ", anonymousObj.toString());

// delete dient dem Löschen von Eigenschaften:
delete anonymousObj["?"];
delete anonymousObj.toString;
log("anonymousObj.toString() [original]", anonymousObj.toString());

// Der Chain-Operator "?." kann verwendet werden, um auf Eigenschaften
// (Properties) von Objekten zuzugreifen, ohne dass eine Fehlermeldung
// ausgegeben wird, wenn eine (höher-liegende) Eigenschaft nicht definiert ist.
// Besonders nützlich beim Verarbeiten von komplexen JSON-Daten.
log("anonymousObj.u?.v.k", anonymousObj.u?.v.k);
log("anonymousObj.u.v?.k", anonymousObj.u.v?.k);
log("anonymousObj.u.v?.z", anonymousObj.u.v?.z);
log("anonymousObj.u.q?.k", anonymousObj.u.q?.k);
log("anonymousObj.p?.v.k", anonymousObj.p?.v.k);

// Nützliche Zuweisungen, um den Fall undefined und null gemeinsam zu behandeln:
anonymousObj.name ||= "Max Mustermann";

log("\nDate ---------------------------------------------------------");
let date = new Date("8.6.2024"); // ACHTUNG: Locale-Settings
log(date);

log("\nFunktionen sind auch Objekte ---------------------------------");
let func = function () {
    return "Hello World";
};
log(func, func());

log("\nArrays -------------------------------------------------------");
let temp = undefined;
let $a = [1];
log("let $a = [1]; $a, $a.length", $a, $a.length);
$a.push(2); // append
log("$a.push(2); $a", $a);
temp = $a.unshift(0); // "prepend" -> return new length
log("temp = $a.unshift(0); temp, $a", temp, $a);
temp = $a.shift(); // remove first element -> return removed element
log("temp = $a.shift(); temp, $a", temp, $a);
// Um zu prüfen ob eine Datenstruktur ein Array ist:
log("Array.isArray($a)", Array.isArray($a));
log("Array.isArray({})", Array.isArray({}));
log("Array.isArray(1)", Array.isArray(1));

log("\nSymbols ------------------------------------------------------");
let sym1 = Symbol("1"); // a unique and immutable primitive value
let sym2 = Symbol("1");
let obj1Values = { sym1: "value1", sym2: "value2" };
log(obj1Values);
log(`sym1 in ${JSON.stringify(obj1Values)}: `, sym1 in obj1Values);
let obj2Values = { [sym1]: "value1", [sym2]: "value2" };
log(obj2Values);
log(`sym1 in ${JSON.stringify(obj2Values)}: `, sym1 in obj2Values);
log(obj1Values, " vs. ", obj2Values);

log({ sym1: "this", sym1: "that" }); // ??? { sym1: "that" }
log("sym1 == sym2", sym1 == sym2);


done();