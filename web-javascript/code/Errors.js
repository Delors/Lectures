"use strict";

console.log("try-catch-finally - Grundlagen ---------------------------------");

try {
  let i = 1 / 0; // Berechnungen erzeugen nie eine Exception
  console.log("i", i);
} catch {
  console.error("console.log failed");
} finally {
  console.log("computation finished");
}

console.log("Programmierfehler behandeln ------------------------------------");
try {
  const obj = {};
  obj = { a: 1 };
} catch ({ name, message }) {
  console.error(name, message);
} finally {
  console.log("object access finished");
}

console.log("Handling of a specific error -----------------------------------");
try {
  throw new RangeError("out of range");
} catch (error) {
  if (error instanceof RangeError) {
    const { name, message } = error;
    console.error("a RangeError:", name, message);
  } else {
    throw error;
  }
} finally {
  console.log("error handling finished");
}
