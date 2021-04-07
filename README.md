# Project Proposal: Virtual Pet

My CPSC 210 personal project idea is to create a virtual pet. This idea was born out of the memories of the 
Tamagotchi digital pets back in the 1990s and early 2000s, so my project is a take on the digital pet character. 
This project will allow users to choose a pet (either a cat or dog), and take care of the pet through feeding and playing 
with their pet. In the future, if possible, I also want to have features where I can move the pet and be able to dress 
the pet in different clothing items, such as a hat or shoes. This app will be accessible to any user who would like to 
create and take care of their own virtual pet.

## User stories:
- As a user, I want to be able to create a pet (dog or cat)
- As a user, I want to be able to name my pet
- As a user, I want to be able to move my pet around
- As a user, I want to be able to feed my pet
- As a user, I want to be able to see how hungry my pet is
- As a user, I want to be able to play with my pet
- As a user, I want to be able to see how happy my pet it
- As a user, I want to be able to pet my pet
- As a user, I want to be able to create multiple pets (dog or cat)
- As a user, I want to be able to view all the pets I have created
- As a user, I want to be able to search up my pets by name
- As a user, I want to be able to save my list of pets to file
- As a user, I want to be able to load my list of pets from file
- As a user, when I select the quit option from the application menu, I want to be reminded to save my pets to file or 
  have the option to not do so
- As a user, when I start the application, I want to be given the option to load my pets from file

## Phase 4: Task 2

I have chosen to implement a type hierarchy for task 2. The PetAnimal class is an abstract class that are extended by 
the Dog and Cat subclasses. feed(), play(), and sleep() are abstract functions that are implemented in the subclasses 
according to the type of animal. 

## Phase 4: Task 3

After taking a look at my UML class diagram, I believe I have done a relatively good job separating functionality between
my classes. Each panel is responsible for a certain part of the GUI, however there are areas in my code that could be streamlined. 
My next steps for refactoring would include:
- Refactor out duplicate code for pet actions, such as dog and cat
  - Currently, in my PetApp class, there are separate methods for dog and cat actions, such as dogFeed and catFeed. I would like to
    create one method that would deal with feeding for different types of animals
- Add a navigator class that refactors duplicate code for navigating between pages
  - Currently, the task of switching between panels are dealt with in the actionListeners, but there the code is 
    relatively the same (revalidate() and removeAll()), so this could be refactored out and instead have a navigator tool class
    that allows me to switch between panels.
- Single point of control
  - I would need to change my code so that it reflects the principle of single point of control and take in the declared field 
    instead of the actual value, in case I would like to change constants in the future, such as font type and font size.
- Create a method or class that deals with importing my dog and cat images and turning them into icons or labels
  - I noticed that I have written the same code multiple times for creating cat and dog images, so I would make an Image to Label class 
    or method, that allows me to pass in an image and size and turn that image into a label of the specified size

