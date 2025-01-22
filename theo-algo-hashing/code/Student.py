class Student: 
    def __init__(self, name, matriculation_number): 
        self._name = name 
        self.matriculation_number = matriculation_number 

    @property
    def name(self):
        return self._name
    @name.setter
    def name(self, name):
        self._name = name
    @name.deleter
    def name(self):
        del self._name

    def __eq__(self, other): 
        if isinstance(other, Student): 
            return  self.name == other.name and \
                    self.matriculation_number == other.matriculation_number 
        return False 

    def __hash__(self): 
        return self.matriculation_number

print("Correct usage:")
student1 = Student("Alice", 123456) 
student2 = Student("Bob", 234567) 
student3 = Student("Alice", 123456) # gleiche Werte wie "student1"
students = {student1, student2, student3}
for s in students: print(s.name)

# THE FOLLOWING DEMONSTRATES THE PROBLEM WITH MUTABLE STRUCTURES
print("Illegal usage:")
student4 = Student("Charlie", 456) 
student1.name = "Charlie" # DON'T DO THIS IN YOUR CODE
students.add(student4)
for s in students: print(s.name)


