class Figure {
  calcArea() {
    throw new Error("calcArea is not implemented");
  }
}
class Rectangle extends Figure {
  height;
  width;

  constructor(height, width) {
    super();
    this.height = height;
    this.width = width;
  }

  calcArea() {
    return this.height * this.width;
  }

  get area() {
    return this.calcArea();
  }

  set area(value) {
    throw new Error("Area is read-only");
  }
}

const r = new Rectangle(10, 20);
console.log("r instanceof Figure", r instanceof Figure); // true
console.log(r.width);
console.log(r.height);
console.log(r.area); // 200

try {
  r.area = 300; // Error: Area is read-only
} catch (e) {
  console.error(e.message);
}

class Queue {
  #last = null;
  #first = null;

  enqueue(elem) {
    if (this.#first === null) {
      const c = { e: elem, next: null };
      this.#first = c;
      this.#last = c;
    } else {
      const c = { e: elem, next: null };
      this.#last.next = c;
      this.#last = c;
    }
  }

  foreach(f) {
    let c = this.#first;
    while (c !== null) {
      f(c.e);
      c = c.next;
    }
  }

  *[Symbol.iterator]() { // implements the iterator protocol using a generator
    let c = this.#first;
    while (c !== null) {
      yield c.e;
      c = c.next;
    }
  }

  isEmpty() {
    return this.#first === null;
  }
}

const q = new Queue();
q.enqueue(1);
console.log("new Queue().enqueue(1).foreach(console.log): "); 
q.foreach(console.log);
for (let v of q) { console.log(v); }

try {
  /* "first" is not the name of the private field!
     Hence, q.first is not our private field and:
     
      console.log("q.first", q.first); 

    would result in a run-time error.
  */
  /* Accessing the private field #first results in a error at load time:
     Load time error: console.log("q.#first", q.#first);
  */
} catch (error) {
  console.error(error);
}
