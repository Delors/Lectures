from pulp import (
    LpProblem,
    LpVariable,
    value,
    lpSum,
    LpInteger
)


VALS = range(10)
LETTERS = ["S", "E", "N", "D", "M", "O", "R", "Y"]

prob = LpProblem("SendMoreMoney")  # We need to find an assignment

choices = LpVariable.dicts("Choice", LETTERS,0,9, cat=LpInteger)

# ensure a "not all null" assignment

prob += (
    choices["S"] * 1000
    + choices["E"] * 100
    + choices["N"] * 10
    + choices["D"]
    + choices["M"] * 1000
    + choices["O"] * 100
    + choices["R"] * 10
    + choices["E"]
    == choices["M"] * 10000
    + choices["O"] * 1000
    + choices["N"] * 100
    + choices["E"] * 10
    + choices["Y"]
)
prob += (choices["S"]
    + choices["E"] 
    + choices["N"] 
    + choices["D"]
    + choices["M"] 
    + choices["O"] 
    + choices["R"] 
    + choices["E"] >= 28
)
prob += (choices["S"]
    + choices["E"] 
    + choices["N"] 
    + choices["D"]
    + choices["M"] 
    + choices["O"] 
    + choices["R"] 
    + choices["E"] <= 45
)
prob.solve()
values = [c+ "=" + str(choices[c].value()) for c in choices]
print("; ".join(values))
