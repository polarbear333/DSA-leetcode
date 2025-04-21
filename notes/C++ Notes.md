# How data is represented in computers:
Data in computers are represented as binary numbers of 0 and 1 as they represent switching on and off in electrical circuits, where 0 represents off and 1 represents on. The binary numerical system represent the states of the circuit flow. The reason why binary is chosen instead of decimal, or hexadecimal is because of the relatively easier representation (less numbers allow easier state representation)

In computer hardware (harddisk, RAM), the origin of the source is always binary representation of 0 and 1, but translated as decimal or letters for human understanding.

# How data is represented in applications through variables:
int variable = 5;
In this case, `int` represents the data type, `variable` represents the identifier (variable name), and `= 5` represents initialize, where we initialize the variable as `5`. The initialize can be excluded in some cases. Let's see the variable output in a C++ code:
```
include iostream
using namespace std;

int main(){
	int variable =5;
	 cout << variable << endl;
}
```


This will print out `5` in the terminal. The variable can be changed to other numbers like `10`, or even negative numbers like `-15`.  However, if the value is too large, we might not see the exact output in the terminal. This is because the data type of `int` in c++ is represented as 32 bit `0,1` units (binary), where the range is [-2^31 ~ 2^31 - 1].

A visualization of 32 bit integer:
0 0 0 0 0 0 0 0 0 .....  0
(2^-31) (2^30) (2^29) ..... (2^0)

For instance, if we have a int variable = 12345678901; it will appear as `-539222987` in terminal as the integer has 34 bit in binary, while `int` can only accept 32 bits as maximum, the 2 overflow bits are excluded, and after excluded them the highest bit becomes 1 in the 2^31 unit, hence it becomes negative.

# Binary and bit
In memory, every binary of 0 and 1 are represented as `bit`, and every 8 bit can be represented as `character` , and they have their own memory address. For instance, 0x16b77f024 (expressed as hexadecimal). We can find or dereference the memory address to change the data, but as this is overly complicated, `variable names` are designed to represent what the data is being pointed by the memory address. Because the data that is being pointed can be changed in later calculations, these variable names stand as an identifier to hold the values. Variable names can be named differently, allowing numbers, upper and lowercases, also symbols like underscores. However, it does not allow numbers being named on the front, and also some predefined names like `int` data type that we've mentioned, or `namespaces` etc.

# Bitwise operations
Besides addition, there are also many bitwise operations that can be performed on binary numbers. These bitwise operations treat the numbers as binary and operator bit-by-bit. The following example will be explained using single-bit examples, treat the operations in bits in terms of `1 = TRUE` and `0 = FALSE`.
* **Bitwise AND(&):**   1 & 1 = 1       0 & 1 = 0       1 & 0 = 0      0 & 0 = 0
* **Bitwise OR  (|):**     1 | 1 = 1         0 | 1 = 1         1 | 0 = 1           0 | 0 = 0
* **Bitwise XOR (^):**  1 ^ 1 = 0        0 ^ 1 = 1        1 ^ 0 = 1        0 ^ 0 = 0
* **Bitwise NOT (~):**   ~1 = 0           ~0 = 1

In addition to these single-bit operations, there are bit-shifting operations that can be done on binary numbers. The **left bit-shift operator (<<)** shifts each bit of the binary number left by the specified number of columns, and the **right bit-shift operator (>>)** shifts each bit of the binary number right by the specified number of columns. For example, below we shift two 8-bit numbers:
* 0000**1**000 << 2 = 00**1**000**00**  
- 0000**1**000 >> 2 = **00**0000**1**0
# Using variables without initializing
We can define variables without initializing any values or strings, for example: `int variables;`
If we run this on the main function, the compiler allocates 32 bit (4 bytes) of memory to hold the integer value. The location of this memory allocation can vary. If the variable is a local variable within a function, it's typically allocated on the stack. If it's a global variable, it's allocated in the data segment. Usually, the value contains whatever "garbage" value was previously present in that memory location.

# Other data types in C++
For integers we have other data types than `int` like:
`short`, `long`, `long long`
Where `short` is 16-bit (2 bytes), `long long` is 64-bit (8 bytes), `long` can be 32-bit or 64 bit depending on the compiler. Alternatively, they are named as `int16_t`, `int32_t` and `int64_t` in C++. In many scenarios of calculations, the numerical values are only positive, hence we have unsigned integers to use only half the range (2^32 -1 to 0). They are named as: `uint16_t`, `uint32_t` and `uint64_t`

Character data type represents using character as a variable: char var_c = `a`
They are represented the same as integer in memory. Alternatively, they are represented as `int8_t` and `uint8_t` in c++, where the size is only 1.  The `ASCII` table is used to represent the characters corresponding to the value in these data types. 

Boolean data type represents the `true` and `false` logic, where 0 represents `true` and 1 represents `false`.  Expressing it in terminal with bool var_b1 = true; we can see it is expressed as `1`. While we can just use a bit to represent boolean, it still uses 8 bits (1 character) to represent it as the basic unit of a processor is 1 character, and performs faster than 1 bit.

Recap:
```
int a; // this variable can range from -2³¹ to +2³¹ - 1 
long b; // this variable can range from -2⁶³ to +2⁶³ - 1 
char c; // this variable can range from -2⁷ to +2⁷ - 1

unsigned int a; // this variable can range from 0 to +2³² - 1 
unsigned long b; // this variable can range from 0 to +2⁶⁴ - 1 
unsigned char c; // this variable can range from 0 to +2⁸ - 1

bool havingFun = true;
bool drinkingSoda = false;
```

**Strings:** In C++, the string type is very similar to Java's String class. However, there are a few key differences. First, Java Strings are **immutable**. On the other hand, one can modify C++ strings. 

Next, the substring command in C++ is `substr`, where `s.substr(i,n)` returns the substring of `s` that starts at position i and is of length n. Also, in Java, String objects can be concatenated with any other object (and the other non-String object will automatically be converted to a String), but in C++, string objects can only be concatenated with other string objects (the conversion is not automatically done for you).

```
string message = "Hi, Niema!"; 
string name = message.substr(4,5); // name would have a value of "Niema"
```

**Comparing Objects:** To compare objects in C++, you simply use the relational operators `== , !=, <, <=, >, >=`. In Java, you only use the relational operators to compare primitives and you use .equals() to compare objects. But in C++, you can do something special. You can "overload" the relational operators, meaning you can specify custom methods that get called when the relational operators are applied to objects.  Thus, in C++, you use relational operators to compare _everything_ (including non-primitives). We'll talk about operator-overloading in more detail later.

```
string name1 = "Niema"; string name2 = "Liz"; 
bool sameName = (name1 == name2); // sameName would have a value of 0, or false
```

**Variable Safety:** In Java, if you declare a local variable but do not initialize it before you try to use it, the compiler will throw an error. However, in C++, if you declare a local variable but do not initialize it before you try to use it, the compiler will NOT throw an error! It will simply use whatever random "garbage data" happened to be at that location in memory! This can cause a LOT of headache when debugging your code, so always remember to initialize your variables in C++! Also, in Java, if you attempt to "demote" a variable (i.e., store it in a smaller datatype) without explicitly casting it as the lower datatype, the compiler will throw an error. However, in C++, the compiler will not complain! Below is perfectly valid C++ code in which we do both: we declare two variables and use them without initializing them, and we then demote to a smaller datatype without explicitly typecasting.

```
int harry; // dummy variable 1 
int lloyd; // dummy variable 2 
bool dumbAndDumber = (harry + lloyd); // C++ will allow this!
```
**Global Variables:** In Java, all variables must be declared either within a class or within a method. In C++, however, variables can be declared outside of functions and classes. These "global variables" can be accessed from any function in a program, which makes them difficult to manage, so try to avoid using them.

```
bool dummy = true; 
class DummyClass { 
	// some stuff here 
};
int main() { 
	cout << dummy; // this is valid 
}
```

**Constant Variables:** In Java, a variable can be made so that it cannot be reassigned by using the final keyword. In C++, the equivalent keyword is const, though there are some subtle differences between Java's final and C++'s const. In Java,  final simply prevents the variable from being reassigned. If the data itself is mutable, it can still be changed. In C++, for a variable that directly references (mutable) data, const will prevent that data from being changed. The C++ const keyword can be a bit tricky, and we'll discuss it in more detail in a few steps.

```
int main() { 
	const string DUMMY_NAME = "Harry"; // DUMMY_NAME cannot be reassigned and "Harry" cannot be modified 
	
	DUMMY_NAME = "Barry"; //This is not allowed! But if DUMMY_NAME were not const it would be OK 
	
	DUMMY_NAME[0] = 'L'; // This is not allowed! But if DUMMY_NAME were not const it would be OK 
}
```

## Classes
Now, we will discuss the differences between **classes** in Java and C++. To help exemplify the differences, we will write a simple Student class in both Java and C++:

```(java)
class Student { // Java public static 
	int numStudents = 0; // declare and define static variable 
	private String name; // declare instance variable 
	
	public Student(String n) { /* CODE */ } // declare and define constructor
	
	public void setName(String n) { /* CODE */ } // declare and define setter
	public String getName() { /* CODE */ } // declare and define getter method }
```

```{C++}
class Student { // C++ 
	public: 
		static int numStudents;  // declare static variable 
		Student(string n); // declare constructor 
		
		void setName(string n); // declare setter method 
		string getName() const; // declare getter method 
	private: 
		string name; // declare instance variable 
}; 
int Student::numStudents = 0; // define static variable 
Student::Student(string n) { /* CODE */ } // define constructor 

void Student::setName(string n) { /* CODE */ } // define setter method 
string Student::getName() const { /* CODE */ } // define getter method
```
* In Java , each individual items must be declared in **private** or **public**, but in C++, we have private and public sections alternatively.
* In Java, we fill out the methods within class, but in C++, only methods are declared. Actual implementations are listed separately outside of the class (prefixed by the class name, with the :: operator separating the class name and the method name).
* In C++, accessor (or "getter") methods are tagged with the keyword const, which prevents the method from modifying instance variables
* In C++, there is a semicolon after the class's closing bracket
## Source and header files in C++

In Java, we would write all the code (declartions and definition) in a .java file, but in C++, it is split between .h (header file, to declare classes and function), and .cpp (source file, filling in the bodies of the function)

### Student.h
```
class Student { 
	public: 
		static int numStudents;       // declare static variable  
		Student(string n);           // declare constructor 
	
	private: 
		string name;                 // declare instance variable \
};
```
### Student.cpp
```
int Student::numStudents = 0; // define static variable 

Student::Student(string n) : name(n) { 
	numStudents++; 
}
```

## References, pointers, memory management

**Objects vs. Primitives in Java:** 
* In Java, the rules for what is stored in a variable are simple: **all object variables store object references**, while **primitive type variables store values directly.**
* Assignment is done by value, so when you assign a primitive variable's value to another variable, the actual value is copied;
* When it is an object variable, the reference is copied and you get two references to the same object.
**Objects and Primitives in C++: **
* In C++, on the other hand, there is no such distinction between object and primitive variables. 
* By default,  _all_ variables, both primitives and objects, actually hold _values_, NOT object references.
* C++, like Java, does assignment by value. However, this means that when one object variable is assigned to another, a copy of the entire object is made (like calling clone in Java).

Let's look at an example below:
```
Student flo("Florence"); 

// Creates Student object with name "Florence" and stores as variable 'flo' 
// Note that we do NOT use the keyword 'new' to create the object. 

Student flo2 = flo; // flo2 stores a copy of the Student, with the same name
```

But what if you want to have two ways to access the same data and avoid this copying-the-data behavior? C++ provides two different concepts that give you related but subtly different ways to do this: **references** and **pointers**.

### **References in C++:** 
C++ references are NOT the same as Java references. Although they are related, how they are used and their syntax is pretty different, so it is best if you simply think of them as different concepts.

References in C++ are simply aliases for existing variables. When you define a reference variable in C++, the variable is treated exactly as another name as the variable you set it to. Thus, when you modify the reference variable, you modify the original variable as well without needing to do anything special.

The syntax for creating a reference variable in C++ is to place an & after the type name in the variable declaration. If T is some type, then T& is the syntax to declare a reference to a T variable. Reference declarations must be combined with assignments except in the case of function parameters (discussed further on the next page).

```cpp
Student lloyd("Lloyd"); 
// creates Student object with name "Lloyd" and stores as variable 'lloyd' 

Student & ref = lloyd; // creates reference to 'lloyd' called 'ref' 

Student harry("Harry"); 
// creates Student object with name "Harry" and stores as variable 'harry'

## Now execute the following:
ref = harry; 
// 'ref' is now a copy of 'harry', so 'lloyd' is ALSO now a copy of 'harry' 

cout << lloyd.getName(); 
// this would print "Harry", as the object of `lloyd` variable's reference is a copy of `henry`
```

### Pointers in C++: 
Pointers in C++ are actually quite similar to references in Java. They are variables that store the _memory address_ of some data, as opposed to storing the data directly. That is, their _value_ is a memory address.

```cpp
Student lloyd("Lloyd"); // initialize Student object 
Student* harry = new Student("Harry"); // initialize Student pointer 

Student* ptr1 = &lloyd; // initialize ptr1 to store the address of 'lloyd' Student* ptr2 = harry; 
// initialize Student pointer pointing to same object as 'harry' 

cout << (*ptr1).getName(); // prints "Lloyd" 
cout << ptr2->getName(); // prints "Harry"
```
As we can see, only `lloyd` stores a Student object, while `harry`, `ptr1`, `ptr2` stores a memory address.

### Pointer pointing to another pointer:
```cpp
Student lloyd("Lloyd"); // initialize Student object 
Student* dumb = &lloyd; // initialize Student pointer to store address of 'lloyd' Student** dumber = &dumb; // initialize Student pointer pointer to store address of 'dumb'
```

### Pointer to point to primitive data:
```cpp
int a = 5; // a is an int-type variable 
int* b = &a; // b is a pointer to an int that stores the address of a

## Now, you can use b to access, and change, a.
*b = 42; // This will change the value that's stored in a's box! 
cout << a; // This will print 42
```

### Memory Management:

 In Java, there is very little that you, the programmer, must do to manage how memory is created or destroyed. 
 
 You allocate new memory for objects using the new keyword, and that memory is allocated on the heap and automatically reclaimed when the object is no longer reachable (you might have heard this being refereed to as Java having a "garbage collector"). Unfortunately, in C++, it's not so simple.

In a nutshell, memory can be allocated in two ways: _automatically_ and _dynamically_ (it's technically a little more complicated than this, but for our purposes, this will do).

The default memory allocation is **automatic**, and it occurs whenever data is created unless you specify otherwise.

For example, the code below uses **automatic** memory allocation:
```cpp
int a = 5; // Creates space for an int at compile time, on the stack 

Student harry("Harry"); 
// Creates space for a Student object at compile time, on the stack
```
**Automatic** memory allocation is nice because, just like its name implies, the memory will also be _reclaimed_ automatically when the variables go out of scope. Note that automatically allocated variables are _usually_ created on the stack, and thus, you don't have to worry too much about them.

On the other hand, **dynamic** memory allocation is done at run time and this memory will persist until _you_ explicitly delete it. For example:
```cpp
int* a = new int(5); // Create a new int on the heap 

Student* harry = new Student("Harry"); // Create a new Student object on the heap.
```
The keyword new tells C++ to allocate the memory on the heap and not to delete that memory allocation until the programmer explicitly requests it, even if all of the variables that point to that memory location go out of scope!

So, you, the programmer, must explicitly ask for all of your dynamically allocated memory to be deleted using the keyword delete. If you do not, you will have what's called a _memory leak_. For example:

```cpp
int* a = new int(5); // Create a new int on the heap 
Student* harry = new Student("Harry"); // Create a new Student object on the heap. 

// Some more code 

delete a; // We're done with a so delete it 
delete harry; // We're done with harry, so delete it 

// Some more code
```

By default, delete will free all the memory directly associated with the data stored in the variable. However, if the object points to other dynamically created data, the default behavior will _not_ delete this nested data and you will again have a memory leak, as in the example below:
```cpp
class Student { // C++ 
	public: 
		Student(string n); 
		void setName(string n); 
		string getName() const; 
	
	private: 
		string* name;   // name is now a pointer 
}; 

Student::Student(string n) { 
	name = new string(n); 
	// name is allocated dynamically (just for illustration, not a good idea) 
} 

int doNothing(string aName) { 
	Student* s = new Student("Sammy"); // Dynamically allocate Sammy 
	// Some code here 
	
	delete s; 
	// Delete memory allocated to sammy 
	// Because Student does not have a destructor defined, 
	// this will NOT delete the Student's name field! 
}
```

Fortunately, when you call delete on an object, C++ will invoke the object's destructor. If it is a class you defined, you must supply a destructor for the object if the object itself has any dynamically created data. So we can fix the code above by adding a destructor to the Student class:
```cpp
class Student { // C++ 
	public: 
		Student(string n); 
		~Student(); // Declare the destructor
		void setName(string n); 
		string getName() const; 
	
	private: 
		string* name;   // name is now a pointer 
}; 

Student::Student(string n) { 
	name = new string(n); 
	// name is allocated dynamically (just for illustration, not a good idea) 
} 

Student::~Student() { 
	delete name; 
}

int doNothing(string aName) { 
	Student* s = new Student("Sammy"); // Dynamically allocate Sammy 
	// Some code here 
	
	delete s; 
	// Delete memory allocated to s
	// This will call the destructor for Student
}
```
### Const

In general, **const** implies that something will not be allowed to be changed. For variables in general, you can place the const keyword before or after the type, and the const keyword makes it so that the variable and the data it stores **can never be modified after initialization**. Below is an example using int objects:
```cpp
const int a = 5; 
// 'a' cannot be modified after this: it will always have a value of 5 int 
const b = 6; 
// 'b' cannot be modified after this: it will always have a value of 6
```
With **pointers**, the const keyword becomes a bit trickier. Below are the different places we can place the const keyword and a description of the result:
```cpp
int a = 5; // create a regular int 
int b = 6; // create a regular int 
const int * ptr1 = &a; 
// can change what ptr1 points to, but can't modify the actual data pointed to 

int const * ptr2 = &a; // equivalent to ptr1 

int * const ptr3 = &a; 
// can modify the data pointed to, but can't change what ptr3 points to 

const int * const ptr4 = &a; 
// can't change what ptr4 points to AND can't modify the actual object itself 

ptr1 = &b; // valid, because I CAN change what ptr1 points to 
*ptr1 = 7; // NOT valid, because I CAN'T modify the data pointed to 
*ptr3 = 7; // valid, because I CAN modify the data pointed to 
ptr3 = &b; // NOT valid, because I CAN'T change what ptr3 points to 
ptr4 = &b; // NOT valid, because I CAN'T change what ptr4 points to 
*ptr4 = 7; // NOT valid, because I can't modify the the data pointed to
```

With **references**, the const keyword isn't too complicated. Basically, it prevents modifying the data being referenced via the const reference. Below are some examples with explanations:
```cpp
int a = 5; // create a regular int 
const int b = 6; // create a const int 
const int & ref1 = a; 
// creates a const reference to 'a' (can't modify the value of a using ref1) 
// This is OK. Even though a is allowed to change, it's OK to have a reference that 
// does not allow you to change it because nothing unexpected will happen. 

int const & ref2 = a; 
// equivalent to ref1 ref1 = 7; 
// NOT valid, because ref1 can't modify the data 

const int & ref3 = b; 
// valid, because you can have const reference to const data 

int & ref4 = b; 
// NOT valid, because you CAN'T have a non-const reference to const data 
// ref4 might change the data but b says it shouldn't be changed, which is unexpected 

int & const ref5 = a; // invalid syntax (const must come before the & symbol)
```

**Global Functions:** In Java, every function must either be an instance method or a static function of a class. C++ supports both of these cases, but it also supports functions that are not part of any class, called "global functions." Typically, every functional C++ program starts with the global function main:
```cpp
int main() { 
	// CODE 
}
```
Notice that this main method has a return value (int), whereas the main methods of Java are void. By convention, a C++ main method returns 0 if it completed successfully, or some non-zero integer otherwise.

**Passing Parameters:** C++ has two parameter-passing mechanisms: _call by value_ (as in Java) and _call by reference_. When an object is passed by value, since C++ objects are not references to objects, the function receives a copy of the actual argument. As a result, the function cannot modify the original, and a lot of time and space might be wasted making a copy of the object. If we want to be able to modify the original object, we must pass the object by reference, which we can do by adding an & after the parameter type as follows:
```cpp
// Successfully swaps the values of the argument variables 
void swap(int & a, int & b) { 
	int temp = a;
	 a = b; 
	 b = temp; 
}
```

```cpp
## pass by reference, even if we dont want to modify:
	
double gcContent(const string & genome) { 
// CODE that does not modify genome 
}
```

## Vectors
The C++ vector has the best features of the `array` and `ArrayList` in Java. A C++ vector has convenient elemental access (using the familiar operator) and can grow dynamically. If T is some type, then vector< T > is a dynamic array of elements of type T. A vector also has the convenient "push" and "pop" functions of a stack (a data type that will be further discussed later in this text), where you can add an element to the back of the vector using the `push_back` function, and you can remove the last element of the vector using the `pop_back` function.
```cpp
vector<int> a; // a vector that is initially empty 
vector<int> a(100); // a vector that initially contains 100 elements a.push_back(0); // add 0 to the end of a 

a.pop_back(); // remove the last element of a 
cout << a[10]; // output the element at index 10 of a
```

**Vector Indexing:** Regarding indexing, the valid indices are between 0 and a.size()-1 (just like in Java). However, unlike in Java, there is no runtime check for legal indices, so accessing an illegal index could cause you to access garbage data without even realizing it.

**Memory:** Like in a Java array, the elements stored in a C++ vector are contiguous in memory (i.e., the elements all show up right after one another). Regarding memory storage, like all other C++ objects, a vector is a value, meaning the elements of the vector are values. If one vector is assigned to another, all elements are copied (unlike in Java, where you would simply have another reference to the same array object).
```
vector<int> a(100); // a vector that initially contains 100 elements 
vector<int> b = a; // b is now a copy of a, so all of a's elements are copied
```

### Input and output in C++
**IO Keywords/Operators/Functions:** In C++, the standard output, standard input, and standard error streams are represented by the cout, cin, and cerr objects, respectively. Also, newline characters can be outputted using the endl keyword. You use the << operator to write to standard output (or to any ostream, for that matter):

```cpp
cout << "Hello, world!" << endl << "My name is Lloyd!" << endl;
```
and you use the >> operator to read from standard input (or any istream, for that matter):
```cpp
int n; // declare the variable to hold the input 
cout << "Please enter n: "; // prompt user 
cin >> n; // read user input and store in variable n
```
The getline method reads an entire line of input (from any istream):
```cpp
string userInput; // declare the variable to hold the input 
getline(cin, userInput); // read a single line from cin and store it in userInput
```

**End of Input:** If the end of input has been reached, or if something could not be read correctly, the stream is set to a failed state, which you can test for using the fail method:
```
int n; 
cin >> n; 
if(cin.fail()) { 
	cerr << "Bad input!" << endl; 
}
```

## Generics & Templates

```java
class Node<Data> { // Java Node class, with generic type "Data" 
specified 
	public final Data data; 
	public Node(Data d) { 
		data = d; 
	} 
}
```

```java
Node<Student> a = new Node<Student>(exampleStudent); 
// a.data is a variable of type 
Student Node<String> b = new Node<String>(exampleString); 
// b.data is a variable of type String
```

In C++, the equivalent of this is the use of **templates**.
```cpp
template<typename Data> // specified generic type "Data" 
class Node { // C++ Node class, which can now use the type "Data" 
public: 
	Data const data; 
	Node(const Data & d) : data(d) {} 
};
```

```cpp
Node<Student> a(exampleStudent); // a.data is a variable of type Student 
Node<string> b(exampleString); // b.data is a variable of type string
```