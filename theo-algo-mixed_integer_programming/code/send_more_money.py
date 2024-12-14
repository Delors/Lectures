from pulp import (
    LpProblem, LpVariable, LpBinary,
    lpSum
)

VALS = range(10)
LETTERS = ["S", "E", "N", "D", "M", "O", "R", "Y"]

prob = LpProblem("SendMoreMoney") # Hier ist kein Optimierungsziel anzugeben.

choices = LpVariable.dicts("Choice", (LETTERS, VALS), cat=LpBinary)

# Nebenbedingungen

# Jeder Buchstabe muss einen Wert haben
for l in LETTERS:
    varsOfLetter = [choices[l][i] for i in VALS]
    prob += lpSum(varsOfLetter) == 1

# Jeder Wert (0..9) darf nur einmal vorkommen
for i in VALS:
    varsOfValue = [choices[l][i] for l in LETTERS]
    prob += lpSum(varsOfValue) <= 1

# Ziel
prob += (
       lpSum([i*choices["S"][i] for i in range(10)]) * 1000
    +  lpSum([i*choices["E"][i] for i in range(10)]) * 100
    +  lpSum([i*choices["N"][i] for i in range(10)]) * 10
    +  lpSum([i*choices["D"][i] for i in range(10)])
    +  lpSum([i*choices["M"][i] for i in range(10)]) * 1000
    +  lpSum([i*choices["O"][i] for i in range(10)]) * 100
    +  lpSum([i*choices["R"][i] for i in range(10)]) * 10
    +  lpSum([i*choices["E"][i] for i in range(10)])
    == lpSum([i*choices["M"][i] for i in range(10)]) * 10000
    +  lpSum([i*choices["O"][i] for i in range(10)]) * 1000
    +  lpSum([i*choices["N"][i] for i in range(10)]) * 100
    +  lpSum([i*choices["E"][i] for i in range(10)]) * 10
    +  lpSum([i*choices["Y"][i] for i in range(10)])
)

# Lösung berechnen
prob.solve()
values = [
    c + "=" + str(i) 
        for c in choices 
        for i in range(10) 
            if choices[c][i].value() == 1
]
print("; ".join(values))
