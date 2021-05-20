package com.nawadata.nfUnitTestLibrary;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import org.openqa.selenium.By;

public class Extensions {
    public static Duration defaultTimeout = Duration.ofSeconds(10);
    public static long defaultTimeout_inSeconds = defaultTimeout.getSeconds();

    public static String XPathToLower(String attribute) { 
        return String.format("translate(%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')", attribute);
    }

    public static String XPathInexactContains(String a, String b) {
        return String.format("contains(%s, '%s')", XPathToLower(a), b.toLowerCase());
    }

    public static By XPath_GetElementByText(String text) {
        return By.xpath(String.format("//*[contains(text(), '%s')]", text));
    }

    public enum ByOption
    {
        ID ("@id"),
        Class ("@class"),
        Name ("@name"),
        Text ("text()");

        private final String name; 

        private ByOption(String s) {
            name = s;
        }
    
        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false 
            return name.equals(otherName);
        }
    
        public String toString() {
            return this.name;
        }
    }

    public static By GetElementContainingString(String text, ByOption by, String tag) {
        return By.xpath(String.format("//%s[%s]", tag, XPathInexactContains(by.toString(), text)));
    }

    public static By GetElementContainingString(String text, ByOption by) {
        return By.xpath(String.format("//%s[%s]", "*", XPathInexactContains(by.toString(), text)));
    }

    public static String appendPathToURLString(String str, String path) throws MalformedURLException {
        URL url = new URL(str);
        URL finalUrl = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getPath() + "/pet" + "?" + url.getQuery(), null);

        return finalUrl.toString();
    }
    
    public static long toUInt32(byte[] bytes, int offset) {
        long result = Byte.toUnsignedLong(bytes[offset+3]);
        result |= Byte.toUnsignedLong(bytes[offset+2]) << 8;
        result |= Byte.toUnsignedLong(bytes[offset+1]) << 16;
        result |= Byte.toUnsignedLong(bytes[offset]) << 24;
        return result;
    }

    private static final Random rand = new Random();
    private static String randstr(int size, char[] charset) {
        byte[] data = new byte[4 * size];
        rand.nextBytes(data);

        StringBuilder result =  new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            long rnd = Math.abs(toUInt32(data, i * 4)); // Java have no concept of unsigned. I don't know how this will behave here, so i add abs() for a good measure.
            int idx = (int)(rnd % charset.length);

            result.append(charset[idx]);
        }

        return result.toString();
    }

    
    static final char[] lowerAlphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static final char[] upperAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static final char[] numbers = "1234567890".toCharArray();
    static final char[] symbols = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray();

    public static char[] mergeCharArrays(char[]... charArrays) {
        int charArrayLength_Total = 0;
        for (char[] charArray : charArrays) {
            charArrayLength_Total += charArray.length;
        }

        StringBuilder strBuilder = new StringBuilder(charArrayLength_Total);

        for (char[] charArray : charArrays) {
            strBuilder.append(charArray);
        }

        return strBuilder.toString().toCharArray();
    }

    public enum RandomStringOption
    {
        Alphabetic,
        Numeric,
        Symbols,
        Alphanumeric,
        Alphanumeric_withSymbols,
    }

    public enum CaseOption {
        LowerOnly,
        UpperOnly,
        Mixed,
    }

    public static Stream<Character> getCharStream(char[] arr) {
        return CharBuffer.wrap(arr).chars().mapToObj(ch -> (char)ch);
    }
    
    public static String RandomString() {
    	return RandomString(24, RandomStringOption.Alphabetic, CaseOption.Mixed);
    }
    
    public static String RandomString(int length) {
    	int hardLimit = 1024;
    	return RandomString(length > hardLimit ? hardLimit : length, RandomStringOption.Alphabetic, CaseOption.Mixed);
    }

    public static String RandomString(int size, RandomStringOption rStringOpt, CaseOption caseOpt) {
        boolean[] optToggle = new boolean[] {false, false, false};

        switch (rStringOpt)
        {
            case Alphabetic:
                optToggle = new boolean[] {true, false, false};
                break;
            case Numeric:
                optToggle = new boolean[] {false, true, false};
                break;
            case Symbols:
                optToggle = new boolean[] {false, false, true};
                break;
            case Alphanumeric:
                optToggle = new boolean[] {true, true, false};
                break;
            case Alphanumeric_withSymbols:
                optToggle = new boolean[] {true, true, true};
                break;
            default:
                break;
        }

        StringBuilder alphabetsBuilder = new StringBuilder();
        switch (caseOpt) {
            case LowerOnly:
                alphabetsBuilder.append(lowerAlphabets);
                break;
            case UpperOnly:
                alphabetsBuilder.append(upperAlphabets);
                break;
            case Mixed:
                alphabetsBuilder.append(lowerAlphabets);
                alphabetsBuilder.append(upperAlphabets);
                break;
            default:
                alphabetsBuilder.append(lowerAlphabets);
                alphabetsBuilder.append(upperAlphabets);
                break;
        }

        char[] alphabetsCharset = alphabetsBuilder.toString().toCharArray();

        StringBuilder strBuilder = new StringBuilder();

        if (optToggle[0] == true) {
            strBuilder.append(alphabetsCharset);
        }

        if (optToggle[1] == true) {
            strBuilder.append(numbers);
        }

        if (optToggle[2] == true) {
            strBuilder.append(symbols);
        }

        char[] charset = strBuilder.toString().toCharArray();
        final boolean[] finalOptToggle = optToggle;

        String res = "";
        res = randstr(size, charset);

        while (!getCharStream(res.toCharArray()).anyMatch(ch -> 
            (new String(alphabetsCharset).contains(ch.toString()) && (finalOptToggle[0] == true)) ||
            (new String(numbers).contains(ch.toString()) && (finalOptToggle[1] == true)) ||
            (new String(symbols).contains(ch.toString()) && (finalOptToggle[2] == true))
        )) {
            res = randstr(size, charset);
        }
        
        return res;
    }
    
    public static String getRandomEmail() {
    	return String.format("%s@%s.com", RandomString(), RandomString());
    }
    
    public static LocalDate RandomDate(LocalDate dateAfter, LocalDate dateBefore) {
    	long randomTime = ThreadLocalRandom.current().nextLong(dateAfter.toEpochDay(), dateBefore.toEpochDay());
    	
    	return LocalDate.ofEpochDay(randomTime);    	
    }
    
    public static String RandomDateString(LocalDate dateAfter, LocalDate dateBefore, String pattern) {
    	System.out.println(dateAfter);
    	System.out.println(dateBefore);
    	long randomTime = ThreadLocalRandom.current().nextLong(dateAfter.toEpochDay(), dateBefore.toEpochDay());
    	
    	return DateTimeFormatter.ofPattern(pattern).format(LocalDate.ofEpochDay(randomTime));
    }
    
    private static LocalDate DateToLocalDate(Date date) {
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static String RandomDateString(String dateAfter, String dateBefore, String pattern) {
    	SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
    	String res = "";
    	
    	try {
    		res = RandomDateString(
        			DateToLocalDate(dateParser.parse(dateAfter)),
        			DateToLocalDate(dateParser.parse(dateBefore)),
        			pattern
    		);
    	}
    	catch (ParseException ex) {
    		// Throw new Exception("Date invalid!");
    		System.out.println("Exception while handling invalid date");
    	}
    	
    	return res;
    }
    
    public static int RandomNumber(int min, int max) {
    	int res = rand.nextInt(max - min) + min;
    	
    	return res;
    }
    
    public static String RandomNumberString(int min, int max) {
    	int res = rand.nextInt(max - min) + min;
    	
    	return Integer.toString(res, 10);
    }
    
    public static <T> T GetRandomElement(T[] arr) {
    	return arr[rand.nextInt(arr.length)];
    }
    
    public static <T> T coalesce(T... params)
    {
        for (T param : params)
            if (param != null)
                return param;
        return null;
    }
}
