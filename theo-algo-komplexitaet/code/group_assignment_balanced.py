# Assessment of user X by user X; -1 is used for the self assessment
ASSESSMENT = [
    [-1, 10,  6,  6,  9,  6,  4,  1,  1,  9,  1,  7,  9,  3,  5, 10],
    [ 7, -1, 10,  8,  1,  2,  8,  9,  8,  7,  1,  2,  8,  3,  1, 10],
    [ 8,  9, -1, 10,  9,  4,  8,  6,  3,  5,  1,  1, 10, 10,  5,  8],
    [ 1,  2,  6, -1,  4,  6,  9,  9,  7,  9,  1,  1,  8,  4,  2,  4],
    [ 6,  8,  9,  2, -1,  6,  5, 10,  3,  8,  1,  1,  9,  7,  7,  1],
    [ 4,  2,  4,  8,  7, -1,  4,  9,  6,  6,  1, 10,  1,  5,  7,  7],
    [ 2,  3,  5,  1,  4,  4, -1,  3,  4, 10,  1,  5,  7,  3,  6, 10],
    [10,  5,  2,  5,  9,  8,  1, -1,  7,  4,  5,  1,  3, 10,  3,  3],
    [ 5,  8,  9,  3,  8,  3,  2, 10, -1, 10,  6,  1,  1,  6,  5,  4],
    [ 7,  3,  4,  7,  7,  8,  6,  2,  1, -1,  2,  1,  5,  1,  1,  8],
    [ 1,  8,  1,  2, 10,  6, 10, 10,  5, 10, -1,  1,  4,  9,  3,  1],
    [ 7,  3, 10,  7,  6,  5,  2,  3,  4,  3,  5, -1,  7,  1,  1,  5],
    [ 2,  8,  6, 10,  9,  4,  8,  1,  5,  8,  1,  4, -1,  8,  1,  9],
    [ 5,  9,  6,  9,  5, 10,  5, 10,  5,  3,  6,  7,  9, -1,  1,  9],
    [ 4, 10,  6,  9,  7,  8,  6,  8,  4,  9, 10,  9,  8,  9, -1,  1],
    [ 3,  2,  1,  4,  8,  4,  3,  2, 10,  7,  1,  1, 10, 10,  4, -1],
]
STUDENTS = range(16)
GROUPS = range(4)
MAX_GROUP_SIZE = 4

best = float("-inf")
calls = 0

def assess_group(group: list[int]) -> int:
    total: int = 0
    for i in range(len(group)):
        for j in range(i + 1, len(group)):
            # Mutual Assessment
            total += ASSESSMENT[group[i]][group[j]]
            total += ASSESSMENT[group[j]][group[i]]
    return total


def assess_solution(solution: list[list[int]]) -> int:
        groupsAssessments = list(map(assess_group, solution))
        average = sum(groupsAssessments) / len(groupsAssessments)
        total = sum(groupsAssessments)
        # we want to avoid very unbalanced groups:
        total -= sum(map(lambda g: abs(g - average), groupsAssessments)) ** 1.25
        return total

def solve(solution: list[list[int]], users: list[int]) -> list[list[int]]:
    global best
    global calls
    calls += 1
    if calls % 100000 == 0:
        print(calls,end="\r")
    if best > 314: # optimal solution found...
        return;

    if len(users) == 0:
        value = assess_solution(solution)
        if value > best:
            best = value
            best_solution = solution
            print("Happiness" ,value, " - " ,solution)
        return

    user = users[0]
    for group in solution:
        if len(group) < MAX_GROUP_SIZE:
            group.append(user)
            solve(solution, users[1:])
            group.pop()

solve([list() for i in range(4)], list(STUDENTS))
print("Calls: ", calls)