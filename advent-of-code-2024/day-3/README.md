## Pattern & Matcher Methods

### java.util.regex.Pattern

A compiled representation of a regular expression.

https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html

`Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");`
* public static Pattern compile(String regex)
* Compiles the given regular expression into a pattern.

`Matcher matcher = pattern.matcher(input);`
* public Matcher matcher(CharSequence input)
* Creates a matcher that will match the given input against this pattern.

### java.util.regex.Matcher
An engine that performs match operations on a character sequence by interpreting a Pattern.

https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html

`matcher.find()`
* public boolean find()
* Attempts to find the next subsequence of the input sequence that matches the pattern.
* Returns true if, and only if, a subsequence of the input sequence matches this matcher's pattern

`matcher.group()`
* public String group()
* Returns the input subsequence matched by the previous match.
