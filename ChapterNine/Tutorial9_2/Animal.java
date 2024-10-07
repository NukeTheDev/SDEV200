// Charles "Nuke" Phillips
// p. 353

package ChapterNine.Tutorial9_2;

public abstract class Animal
{
   private String name;
   public abstract void speak();
   public String getName()
   {
      return name;
   }
   public void setName(String animalName)
   {
      name = animalName;
   }
}