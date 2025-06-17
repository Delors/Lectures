import { ilog, log, done } from "./log.mjs";

const arr = [1, 3, 4, 7, 11, 18, 29];

log("if-else_if-else:");
if (arr.length == 7) {
    ilog("arr.length == 7");
} else if (arr.length < 7) {
    ilog("arr.length < 7");
} else {
    ilog("arr.length > 7");
}

log("\nswitch (integer value):");
switch (arr.length) {
    case 7:
        ilog("arr.length == 7");
        break;
    case 6:
        ilog("arr.length == 6");
        break;
    default:
        ilog("arr.length != 6 and != 7");
}

log("\nswitch (string value):");
switch ("foo") {
    case "bar":
        ilog("it's bar");
        break;
    case "foo":
        ilog("it's foo");
        break;
    default:
        ilog("not foo, not bar");
}

log("\nswitch (integer - no type conversion):");
switch (
    1 // Vergleich auf strikte Gleichheit (===)
) {
    case "1":
        ilog("string(1)");
        break;
    case 1:
        ilog("number(1)");
        break;
}

ilog("\nfor-continue:");
for (let i = 0; i < arr.length; i++) {
    const v = arr[i];
    if (v % 2 == 0) continue;
    log(v);
}

ilog("\n(for)-break with label:");
outer: for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < i; j++) {
        if (j == 3) break outer;
        log(arr[i], arr[j]);
    }
}

ilog("\nin (properties of Arrays; i.e. the indexes):");
for (const key in arr) {
    log(key, arr[key]);
}

ilog("\nof (values of Arrays):");
for (const value of arr) {
    log(value);
}

ilog("\nArray and Objects - instanceof:");
log("arr instanceof Object", arr instanceof Object);
log("arr instanceof Array", arr instanceof Array);

const obj = {
    name: "John",
    age: 30,
    city: "Berlin",
};

ilog("\nin (properties of Objects):");
for (const key in obj) {
    log(key, obj[key]);
}

/*  TypeError: obj is not iterable

    for (const value of obj) {
        log(value);
        }
*/

{
    ilog("\nIteration über Iterables (here: Map):");
    const m = new Map();
    m.set("name", "Elisabeth");
    m.set("alter", 50);
    log("Properties of m: ");
    for (const key in m) {
        log(key, m[key]);
    }
    log("Key-Values of m: ");
    for (const [key, value] of m) {
        log(key, value);
    }
}

{
    ilog("\nWhile Loop: ");
    let c = 0;
    while (c < arr.length) {
        const v = arr[c];
        if (v > 10) break;
        log(v);
        c++;
    }
}

{
    ilog("\nDo-While Loop: ");
    let c = 0;
    do {
        log(arr[c]);
        c++;
    } while (c < arr.length);
}

done();
