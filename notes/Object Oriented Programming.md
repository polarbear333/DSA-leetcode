
# Encapsulation, Inheritance and Polymorphorism

We will be using the game Plant & Zombies as an example for easier interpretation. In the game, there are many different types of objects, like zombies, plants, bullets, sun , cart etc. We will have to create different types to represent them in game. While some types have similar characteristics and variables, they have parts that are distinct to each other so we would have to initialize them differently as object. For instance, the character usually have variables like: `x` , `y` (positions), width,
height, life, attack, test() (collision test function). 

If we create all these objects as code distinctly to represent them one by one, it would use a significant amount of repetitive code. We would use inheritance to solve this kind of issue, as it allows inheriting another class' variable and funciton. Here's an example in code:

class Obj{
	double x, y, w, h;
	 int life, attack;
public:
	bool test(Obj* other);
};
	class Plant: Obj{
	int coolDown;	
   }

where `Plant` represents the child class and `Obj` represents the parent class, we can also then create a `Peashooter` for a specific implementation of inheriting `Plant` class:

class Peashooter: Plant{
	int lastShootTime;
	 void Shoot();
}