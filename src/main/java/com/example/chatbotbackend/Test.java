package com.example.chatbotbackend;

import java.util.HashSet;
import java.util.Set;

public class Test {
  public static boolean isPangramme(String input) {
    Set<Character> set = new HashSet<>();

    for (int i = 0; i < input.length(); i++) {
      if (Character.toLowerCase(input.charAt(i)) >= 97 && Character.toLowerCase(input.charAt(i)) <= 122)
        set.add(Character.toLowerCase(input.charAt(i)));
    }

    return set.size() == 26;
  }

  public static void main(String[] args) {
    System.out.println(isPangramme("qwertyuiopasdfghjklzz ?!lknkjn   ?xcvbnm"));
  }
}
