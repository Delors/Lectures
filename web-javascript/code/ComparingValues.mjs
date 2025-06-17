import { log as show, ilog as log, done } from "./log.mjs";

// Gleichheit           ==  // mit Typumwandlung (auch bei <, >, <=, >=)

// strikt gleich        === // ohne Typumwandlung
// strike Ungleichheit  !== // ohne Typumwandlung

log('1 == "1": ', 1 == "1");
log('1 === "1": ', 1 === "1");
log("1.0 == 1: ", 1 == 1.0);
log("1.0 === 1: ", 1 === 1.0);
log("1 === 1n: ", 1 === 1n); // 1n ist ein bigint mit den Wert 1
log("1 == 1n: ", 1 == 1n);
log('1 < "1"', 1 < "1");
log('0 < "1"', 0 < "1");
log('0 <= "0"', 0 <= "0");
log('"abc" <= "d"', "abc" <= "d");

log('"asdf" === "as" + "df"', "asdf" === "as" + "df"); // unlike Java!

log("NaN === NaN: ", NaN === NaN);
log("NaN == NaN: ", NaN == NaN);
log("null === NaN: ", null === NaN);
log("null == NaN: ", null == NaN);
log("null === null: ", null === null);
log("null == null: ", null == null);
log("undefined === undefined: ", undefined === undefined);
log("undefined == undefined: ", undefined == undefined);
log("null === undefined: ", null === undefined);
log("null == undefined: ", (null == undefined) + "!");

const a1 = [1, 2, 3];
const a2 = [1, 2, 3];
log("const a1 = [1, 2, 3]; a1 == [1, 2, 3]: ", a1 == [1, 2, 3]);
log("const a1 = [1, 2, 3]; a1 == a1: ", a1 == a1);
log("const a1 = [1, 2, 3]; a1 === a1: ", a1 === a1);
log("const a1 = [1, 2, 3]; const a2 = [1, 2, 3]; a1 === a2: ", a1 === a2);
log("const a1 = [1, 2, 3]; const a2 = [1, 2, 3]; a1 == a2: ", a1 == a2);
log(
    "flatEquals(a1,a2):",
    a1.length == a2.length && a1.every((v, i) => v === a2[i]),
);

let firstJohn = { person: "John" };
show('let firstJohn = { person: "John" };');
let secondJohn = { person: "John" };
show('let secondJohn = { person: "John" };');
let basedOnFirstJohn = Object.create(firstJohn);
show("let basedOnFirstJohn = Object.create(firstJohn)");
log("firstJohn == firstJohn: ", firstJohn == firstJohn);
log("firstJohn === secondJohn: ", firstJohn === secondJohn);
log("firstJohn == secondJohn: ", firstJohn == secondJohn);
log("firstJohn === basedOnFirstJohn: ", firstJohn === basedOnFirstJohn);
log("firstJohn == basedOnFirstJohn: ", firstJohn == basedOnFirstJohn);

{
    const obj = {
        name: "John",
        age: 30,
        city: "Berlin",
    };
    show("Typtests und Feststellung des Typs:");
    log("typeof obj", typeof obj);
    log("obj instanceof Object", obj instanceof Object);
    log("obj instanceof Array", obj instanceof Array);

    // ⚠️ Das Array Objekt ist Kontextabhängig:
    //
    //      iframe.contentWindow.Array !== window.Array;
    //
    // D.h. ein instanceof Check liefert ggf. unerwartete Resultate
}
{
    const obj = { a: "lkj" };
    const obj2 = Object.create(obj);
    log("obj2 instanceof obj.constructor", obj2 instanceof obj.constructor);
}

log("\n?-Operator/if condition and Truthy and Falsy Values:");
log('""', "" ? "is truthy" : "is falsy");
log("f()", (() => {}) ? "is truthy" : "is falsy");
log("Array ", Array ? "is truthy" : "is falsy");
log("obj ", {} ? "is truthy" : "is falsy");
log("undefined ", undefined ? "is truthy" : "is falsy");
log("null ", null ? "is truthy" : "is falsy");
log("0", 0 ? "is truthy" : "is falsy");
log("1", 1 ? "is truthy" : "is falsy");

done();
