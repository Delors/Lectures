#!/Applications/SWI-Prolog.app/Contents/MacOS/swipl -f -q

:- ensure_loaded("sat_model.pl").

:- initialization main.

main :- 
    forall( % print _ALL_ solutions
        ((tf(A),tf(B),tf(C),tf(D)),c(A,B,C,D)), 
        writeln([A,B,C,D])
    ),
    halt.