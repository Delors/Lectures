// Tested with Java23/24 and "jshell --enable-preview"

class Student {
    private String name;
    private int matriculationNumber;

    public Student(String name, int matriculationNumber) { 
        this.name = name; 
        this.matriculationNumber = matriculationNumber; 
    }

    public int getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(int matriculationNumber) { 
        this.matriculationNumber = matriculationNumber; // DON'T DO THIS IN YOUR CODE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { 
        this.name = name; // DON'T DO THIS IN YOUR CODE
    }

    @Override
    public boolean equals(Object o) { 
        if (o instanceof Student) { 
            Student other = (Student) o; 
            return this.matriculationNumber == other.matriculationNumber && this.name.equals(other.name) /* Including the name is questionable! */; 
        } 
        return false; 
    }

    @Override
    public int hashCode() { 
        return matriculationNumber;
    }
}


System.out.println("Correct usage:");
var student1 = new Student("Alice", 123456);
var student2 = new Student("Bob", 234567);
var student3 = new Student("Alice", 123456); // gleiche Werte wie "student1"
var students = new HashSet<Student>();
students.add(student1);
students.add(student2);
students.add(student3); 
for (var s : students) System.out.println(s.getName());

// THE FOLLOWING DEMONSTRATES THE PROBLEM WITH MUTABLE STRUCTURES
System.out.println("Illegal usage:");
var student4 = new Student("Charlie", 456);
student1.setName("Charlie"); // DON'T DO THIS IN YOUR CODE
students.add(student4);
for (var s : students) System.out.println(s.getName());


