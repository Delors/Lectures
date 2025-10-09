new java.util.Random().nextInt();
final var key = new java.util.Random().nextInt();
IO.println("0".repeat(Integer.numberOfLeadingZeros(key)) + Integer.toBinaryString(key));
final var income = 13423;
IO.println("0".repeat(Integer.numberOfLeadingZeros(income)) + Integer.toBinaryString(income));
final var encryptedIncome = income ^ key;
IO.println("0".repeat(Integer.numberOfLeadingZeros(encryptedIncome)) + Integer.toBinaryString(encryptedIncome));