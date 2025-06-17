//"use strict";

function counter() {
    // console.log(this === globalThis); // true
    if (this.count)
        // this is the global object if we don't use strict mode
        this.count++;
    else {
        this.count = 1;
    }

    return this.count;
}

const counterExpr = function () {
    if (this.count) this.count++;
    else {
        this.count = 1;
    }

    return this.count;
};

const counterArrow = () => {
    console.log(this);
    console.log(this === globalThis);
    this.count = this.count ? this.count + 1 : 1;
    return this.count;
};

console.log("\nCounter");
console.log(counter()); // 1
console.log(counter()); // 2
console.log(`globalThis.count (${globalThis.count})`);

console.log("\nCounterExpression");
console.log(counterExpr()); // 3
console.log(counterExpr()); // 4

console.log("\nCounter");
const obj = {}; // empty
console.log(counter.apply(obj)); // 1 - we pass in obj as "this"!
console.log(counterExpr.apply(obj)); // 2

console.log(`\nCounterArrow (${this.count})`);
console.log(counterArrow.apply(obj)); // 1
console.log(counterArrow.apply(undefined)); // 2
console.log(counterArrow.apply()); // 3
console.log(counterArrow.apply(obj)); // 4
console.log(counterArrow.apply({})); // 5

console.log("\nCounter (global)");
console.log(counter());
console.log(counterExpr());
