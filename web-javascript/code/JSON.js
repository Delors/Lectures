const someJSON = `{
    "name": "John",
    "age": 30,
    "cars": {
        "American": ["Ford"],
        "German": ["BMW", "Mercedes", "Audi"],
        "Italian": ["Fiat","Alfa Romeo", "Ferrari"]
    }
}`;

// JSON.parse(...) JSON String => JavaScript Object
const someObject = JSON.parse(someJSON);

someObject.age = 31;
someObject.cars.German.push("Porsche");
someObject.cars.Italian.pop();
console.log(someObject);

// JSON.stringify(...) JavaScript Object => JSON String
console.log(JSON.stringify(someObject, null, 2));
