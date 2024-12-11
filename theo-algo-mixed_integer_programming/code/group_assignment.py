from pulp import (
    LpProblem,
    LpVariable,
    LpMaximize,
    LpStatus,
    value,
    lpSum,
    allcombinations,
    LpBinary
)


# Assessment of user X by user X; -1 is used for the self assessment
ASSESSMENT = [
    [-1, 10, 6, 6, 9, 6, 4, 1, 1, 9, 1, 7, 9, 3, 5, 10],
    [7, -1, 10, 8, 1, 2, 8, 9, 8, 7, 1, 2, 8, 3, 1, 10],
    [8, 9, -1, 10, 9, 4, 8, 6, 3, 5, 1, 1, 10, 10, 5, 8],
    [1, 2, 6, -1, 4, 6, 9, 9, 7, 9, 1, 1, 8, 4, 2, 4],
    [6, 8, 9, 2, -1, 6, 5, 10, 3, 8, 1, 1, 9, 7, 7, 1],
    [4, 2, 4, 8, 7, -1, 4, 9, 6, 6, 1, 10, 1, 5, 7, 7],
    [2, 3, 5, 1, 4, 4, -1, 3, 4, 10, 1, 5, 7, 3, 6, 10],
    [10, 5, 2, 5, 9, 8, 1, -1, 7, 4, 5, 1, 3, 10, 3, 3],
    [5, 8, 9, 3, 8, 3, 2, 10, -1, 10, 6, 1, 1, 6, 5, 4],
    [7, 3, 4, 7, 7, 8, 6, 2, 1, -1, 2, 1, 5, 1, 1, 8],
    [1, 8, 1, 2, 10, 6, 10, 10, 5, 10, -1, 1, 4, 9, 3, 1],
    [7, 3, 10, 7, 6, 5, 2, 3, 4, 3, 5, -1, 7, 1, 1, 5],
    [2, 8, 6, 10, 9, 4, 8, 1, 5, 8, 1, 4, -1, 8, 1, 9],
    [5, 9, 6, 9, 5, 10, 5, 10, 5, 3, 6, 7, 9, -1, 1, 9],
    [4, 10, 6, 9, 7, 8, 6, 8, 4, 9, 10, 9, 8, 9, -1, 1],
    [3, 2, 1, 4, 8, 4, 3, 2, 10, 7, 1, 1, 10, 10, 4, -1],
]
STUDENTS = range(16)
GROUPS = range(4)
MAX_GROUP_SIZE = 4


prob = LpProblem("Group Assignment",LpMaximize)  # We need to find an assignment

# we generate all possible solutions to make it possible to model our happiness function

def happiness_of_group(group):
    total = 0
    for s1 in group:
        for s2 in group:
            if s1 != s2:
                total += ASSESSMENT[s1][s2]
    return total

possible_groups = [g for g in allcombinations(STUDENTS,MAX_GROUP_SIZE) if len(g) == MAX_GROUP_SIZE]

# we create a binary variable for each possible group
choices = LpVariable.dicts("Choice", possible_groups, cat=LpBinary)

prob += lpSum([happiness_of_group(g) * choices[g] for g in possible_groups])  # maximize the total assessment


# each student must be assigned to exactly one group
for s in STUDENTS:
    constraint = [choices[g] for g in possible_groups if s in g]
    prob += lpSum(constraint) == 1

prob.solve()
print(LpStatus[prob.status])
print(value(prob.objective))
for g in possible_groups:
    if value(choices[g]) == 1:
        print(g, ":", happiness_of_group(g))