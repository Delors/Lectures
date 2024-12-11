from pulp import (
    LpProblem,
    LpVariable,
    value,
    lpSum,
    LpBinary,
)


VALS = range(10)
LETTERS = ["S", "E", "N", "D", "M", "O", "R", "Y"]

prob = LpProblem("SendMoreMoney")  # We need to find an assignment

choices = LpVariable.dicts("Choice", (LETTERS, VALS), cat=LpBinary)

# each letter has to have a value
for l in LETTERS:
    constraint = [choices[l][i] for i in VALS]
    prob += lpSum(constraint) == 1

# each value must appear at most once
for i in VALS:
    constraint = [choices[l][i] for l in LETTERS]
    prob += lpSum(constraint) <= 1

prob += (
    lpSum([i*choices["S"][i] for i in range(10)]) * 1000
    + lpSum([i*choices["E"][i] for i in range(10)]) * 100
    + lpSum([i*choices["N"][i] for i in range(10)]) * 10
    + lpSum([i*choices["D"][i] for i in range(10)])
    + lpSum([i*choices["M"][i] for i in range(10)]) * 1000
    + lpSum([i*choices["O"][i] for i in range(10)]) * 100
    + lpSum([i*choices["R"][i] for i in range(10)]) * 10
    + lpSum([i*choices["E"][i] for i in range(10)])
    == lpSum([i*choices["M"][i] for i in range(10)]) * 10000
    + lpSum([i*choices["O"][i] for i in range(10)]) * 1000
    + lpSum([i*choices["N"][i] for i in range(10)]) * 100
    + lpSum([i*choices["E"][i] for i in range(10)]) * 10
    + lpSum([i*choices["Y"][i] for i in range(10)])
)
prob.solve()
values = [c+ "=" + str(i) for c in choices for i in range(10) if value(choices[c][i]) == 1]
print("; ".join(values))
