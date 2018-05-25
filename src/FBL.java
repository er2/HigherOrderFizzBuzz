import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class FBL
{

   public static void main(String[] args)
   {
      IntStream.rangeClosed(1, 100)
           .mapToObj(FBL::fizzbuzz)
           .forEach(System.out::println);
   }

   @FunctionalInterface
   interface StageGenerator
   {
      UnaryOperator<String> generate(IntPredicate test, String output, UnaryOperator<String> func);
   }

   private static String fizzbuzz(int n)
   {
      StageGenerator sg = (test, output, func) -> {
         if (test.test(n))
         {
            return g -> output + func.apply("");
         }
         else
         {
            return func;
         }
      };
      UnaryOperator<UnaryOperator<String>> fizz = f -> sg.generate(k -> k % 3 == 0, "Fizz", f);
      UnaryOperator<UnaryOperator<String>> buzz = f -> sg.generate(k -> k % 5 == 0, "Buzz", f);
      UnaryOperator<String> id = s -> s;
      return fizz.apply(buzz.apply(id)).apply(Integer.toString(n));
   }

}
