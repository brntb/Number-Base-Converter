# HyperSkill-Number-Base-Converter

# Description

Fractional numbers can also be converted from one base to another, so let's add this functionality to our program!

To convert a fractional number from one base to another, you need to split the number into two parts: integer and fractional. Convert each part from their base to decimal independently and then convert them (once again, separately) to the target base. Finally, combine both parts and get the final result!

The most challenging part will probably be converting the decimal fractional part to the target base. Don't worry, though: since you already know how to convert fractions from decimal to binary, it should not be a problem for you! Converting fractions from decimal to any base is practically the same: just use the proper denominator, which is the target base, instead of 2.

The example below shows how to convert the number 0.375 from decimal to base 20:
Fractional 	Integer
0.375 * 20 = 	7 + 0.5
0.5 * 20 = 	10 + 0.0

Result: 0.37510=0.7a200.375_{10} = 0.7a_{20}0.37510​=0.7a20​

Just like in the previous stage, the input numbers can be large. You might want to consider using java.math.BigDecimal to handle large fractions. Check the documentation on BigDecimal.
Objectives

Your program should behave almost the same way as in the previous stage: the two-level menu and the commands stay the same.

When your program gets a fractional number, it should output its representation in the target base rounded to 5 characters (digits or letters) in the fractional part. If there is no fractional part in the initial number, it should be omitted in the resulting number, too.
Example

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 10 7
Enter number in base 10 to convert to base 7 (To go back type /back) > 0.234
Conversion result: 0.14315

Enter number in base 10 to convert to base 7 (To go back type /back) > 10.234
Conversion result: 13.14315

Enter number in base 10 to convert to base 7 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 35 17
Enter number in base 35 to convert to base 17 (To go back type /back) > af.xy
Conversion result: 148.g88a8

Enter number in base 35 to convert to base 17 (To go back type /back) > aaaa.0
Conversion result: 54e36.00000

Enter number in base 35 to convert to base 17 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 21 10
Enter number in base 21 to convert to base 10 (To go back type /back) > 4242
Conversion result: 38012

Enter number in base 21 to convert to base 10 (To go back type /back) > 4242.13a
Conversion result: 38012.05550

Enter number in base 21 to convert to base 10 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > /exit
