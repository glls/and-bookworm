package com.totsp.bookworm.util;

import com.totsp.bookworm.model.Author;

import java.util.ArrayList;

public final class StringUtil {

   private StringUtil() {
   }

   public static ArrayList<Author> expandAuthors(final String in) {
      ArrayList<Author> authors = new ArrayList<Author>();
      if (in != null) {
         if (in.contains(",")) {
            String[] authorsArray = in.split(",\\s*");
            for (int i = 0; i < authorsArray.length; i++) {
               authorsArray[i] = authorsArray[i].replaceAll("^[\"]?(.*?)[\"]?$", "$1");
               authors.add(new Author(authorsArray[i]));
            }
         } else {
            String authorName = in.replaceAll("^[\"]?(.*?)[\"]?$", "$1");
            authors.add(new Author(authorName));
         }
      }
      return authors;
   }

   public static String contractAuthors(final ArrayList<Author> authors) {
      String result = null;
      if (authors.size() == 1) {
         result = authors.get(0).name;
      } else {
         // avoid enhanced for loop on Android with ArrayList
         for (int i = 0; i < authors.size(); i++) {
            Author a = authors.get(i);
            if (i == 0) {
               result = a.name;
            } else {
               result += ", " + a.name;
            }
         }
      }
      if (result == null) {
         result = "";
      }
      return result;
   }

   public static String addSpacesToCSVString(final String in) {
      StringBuilder sb = new StringBuilder();
      if (in != null) {
         if (in.contains(",")) {
            String[] authorsArray = in.split(",\\s*");
            for (int i = 0; i < authorsArray.length; i++) {
               if (i == 0) {
                  sb.append(authorsArray[i]);
               } else {
                  sb.append(", " + authorsArray[i]);
               }
            }
         } else {
            return in;
         }
      }
      return sb.toString();
   }

}
