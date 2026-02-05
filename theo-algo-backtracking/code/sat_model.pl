% Fakten
tf(true).
tf(false).

% (A ∨ ¬B) ∧ (¬A ∨ B) ∧ (¬A ∨ ¬C) ∧ (C ∨ D) ∧ (¬C ∨ ¬D)
c_pure(A, B, C, D) :-
    (A; not(B)), 
    (not(A); B), 
    (not(A); not(C)), 
    (C; D), 
    (not(C); not(D)).

% By defining the logical operators, we can use the infix notation for them
% and the prefix notation for the negation operator.
:- op(200, fy, ¬).
:- op(300, xfy, ∧).
:- op(400, xfy, v).
¬(false).
v(A,B) :- A; B, !.
∧(A,B) :- A, B, !.

l(A,B,C) :- 
    (A v ¬(B)) ∧ (B v C).

c(A, B, C, D) :-
    (A v ¬ B) ∧
    (¬ A v B) ∧
    (¬ A v ¬ C ) ∧ 
    (C v D) ∧
    (¬ C v ¬ D).

/*
Example interaction:

# Loading the file:
/Applications/SWI-Prolog.app/Contents/MacOS/swipl sat_model.pl 

# At the command line:

?-  tf(A),tf(B),tf(C),tf(D), % Generate all possible values for A, B, C, D
    c(A,B,C,D). % When c is called, A, B, C, D need to be bound to a value
*/