
# Encapsulation, Inheritance and Polymorphorism

We will be using the game Plant & Zombies as an example for easier interpretation. In the game, there are many different types of objects, like zombies, plants, bullets, sun , cart etc. We will have to create different types to represent them in game. While some types have similar characteristics and variables, they have parts that are distinct to each other so we would have to initialize them differently as object. For instance, the character usually have variables like: `x` , `y` (positions), width,
height, life, attack, test() (collision test function). 

If we create all these objects as code distinctly to represent them one by one, it would use a significant amount of repetitive code. We would use inheritance to solve this kind of issue, as it allows inheriting another class' variable and funciton. Here's an example in code:

```
class Obj{
	double x, y, w, h;
	 int life, attack;
public:
	bool test(Obj* other);
};
	class Plant: Obj{
	int coolDown;	
   }
```

where `Plant` represents the child class and `Obj` represents the parent class, we can also then create a `Peashooter` for a specific implementation of inheriting `Plant` class:

```
class Peashooter: Plant{
	int lastShootTime;
	 void Shoot();
}
```

## <u>Liskov Substitution Principle</u>

Lsp states that objects of a superclass should be replaceable with objects of its subclass without affecting the correctness of the program. In simpler terms, if class B is a sutype of class A, we should be able to replace instances of A with instances of B without disrupting the behavior of our program. 

**Inheritance**, while one of the pillars of object-oriented programming, **is invasive**. This is because subclasses are forced to inherit all properties and methods of the superclass, even if they don't need them. This means that **subclasses will be forced to have properties and methods they don't need, and methods in the superclass will be modified by the subclass.**

**The main function of LSP is to restrict the invasiveness of inheritance.**

> [!INFO] Recap
> The term “invasive” in the context of inheritance in OOP refers to a situation where a superclass imposes its structure and behaviour on its subclasses. **Invasiveness is the main drawback of inheritance**. Here are a few reasons why inheritance is considered invasive:
> 
> - **Limited flexibility:** Subclasses are forced to inherit all properties and methods of the superclass, even if they don't need them. This can lead to a lack of flexibility and can create unnecessary dependencies.
> - **Exposing Internal Details:** The superclass often exposes its internal details to subclasses, which goes against the principle of encapsulation. This can make the system more complex and harder to understand. 
> - **Tight coupling:** Inheritance creates a very tight coupling between the superclass and its subclasses. Changes in the superclasses can have ripple effects on all subclasses, which can lead to fragile code that is hard to maintain.  
> - **Difficulty in Refactoring:** Refactoring a hierarchy of classes that use inheritance can be difficult and risky. Since subclasses are so tightly coupled to their superclasses, changes in one class can unintentionally affect others.
> - **Inappropriate Abstractions:** Sometimes, inheritance is used to share code rather than create a proper `is-a` relationship. This can lead to inappropriate abstractions and design that does not properly reflect the intended relationships between objects.

### Applying LSP in Practice:
#### 1. Examples of Violating LSP:
```java
public class Bird{
	public void fly(){/* */}

	public void layEggs(){/* ... */}
}

public class Pigeon extends Bird{
	//Pigeon can fly and layEggs
}

public class Ostrich extends Bird{
	@Override
	public void fly(){
		throw new UnsupportedOperationException("Ostriches can't fly");
	}
}
```

Here, `Ostrich` **overrides the non-abstract methods** `fly()` of `Bird`. Substituting `Bird` with `Ostrich` changes the behavior of `fly()`, violating LSP.

Besides this, due to the drawbacks and invasiveness of inheritance, `Ostrich` **is forced to have behavior:** `fly()`.

#### 2. Correct LSP Violation:
```java
public interface Flying{
	void fly();
}

public class Bird{
	public void layEggs(){ /* ... */}
}

public class Pigeon extends Bird implementing Flying{
	@Override
	public void fly(){
		// FlyingBird specific fly implementation
	}
}

public class Ostrich extends Bird{
	//Ostrich can't fly
}
```

This is the corrected design, `fly()` is placed as an abstract method in an `interface` for flying birds to implement. **All the non-abstract methods of** `Bird` **have not been overridden**. So, the behavior of `Bird` will not be changed by its child class. This design adheres to LSP.

### Avoid overriding non-abstract methods

- Applying LSP in practice is very simple and only one thing is needed to be observed:
	- Avoid overriding non-abstract methods 

This is because as long as the non-abstract methods are not overridden, the subclass will not change the behavior of the superclass.
- In fact, even without considering LSPs, **overriding non-abstract methods is still strange behavior and should not be recommended.** Because:
	- A non-abstract method in a superclass can be seen as a convention, which represents itself and its child classes should have such behavior. **Overriding a non-abstract method is a violation of convention.**
		- For example, `Fly()` is implemented in Bird that a bird must be able to fly, are ostriches birds or not? **This inheritance does not seem strong.**
	- If a method of a class may be overridden, it means that the method is not a common feature. **Overriding a non-abstract method indicates that the method shouldn't be implemented in the superclass.**
		- For example, `Ostrich` is a subclass of `Bird` suggesting that fly is not a common feature of birds.
		- In this case, why implement `fly()` in `Bird`? **That is not a reasonable convention.**
	
> [!INFO] Conclusion
> Subclasses should extend, rather than modify the functionality of the superclasses.


	