import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class FBL {

   public static void main(String[] args) {
      IntStream.rangeClosed(1, 100)
           .mapToObj(FBL::fizzbuzz)
           .forEach(System.out::println);
   }

   private static String fizzbuzz(int n) {
      UnaryOperator<UnaryOperator<String>> fizz = f -> {
         if (n % 3 == 0) {
            return g -> "Fizz" + f.apply("");
         } else {
            return f;
         }
      };
      UnaryOperator<UnaryOperator<String>> buzz = f -> {
         if (n % 5 == 0) {
            return g -> "Buzz" + f.apply("");
         } else {
            return f;
         }
      };
      return fizz.apply(buzz.apply(f -> f)).apply(Integer.toString(n));
   }

}
