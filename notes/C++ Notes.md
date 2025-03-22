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
