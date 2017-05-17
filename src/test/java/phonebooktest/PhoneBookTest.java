package phonebooktest;

import exceptions.InvalidPhoneNumberFormatException;
import exceptions.RecordNotPresent;
import org.junit.Assert;
import org.junit.Test;
import phonebook.PhoneBook;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anthonyjones on 5/16/17.
 */
public class PhoneBookTest {

    @Test
    public void lookUpTest() {
        //Given
        Map<String, String> phoneInfo = new HashMap<>();
        phoneInfo.put("Sarah Silverman", "(302)-434-1849");
        String name = "Sarah Silverman";
        String expected = "(302)-434-1849";
        PhoneBook phoneBook = new PhoneBook();
        //When
        String actual = phoneBook.lookUp(name);
        //Then
        Assert.assertEquals("The number of Sarah is \"(302)-434-1849\" ", expected, actual);
    }

    @Test
    public void addEntryTest() throws InvalidPhoneNumberFormatException {
        //Given
        String input = "Dan Alixk\n(492)-797-1823\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        PhoneBook phoneBook = new PhoneBook(byteArrayInputStream);
        String name = "Dan Alixk";
        String number = "(492)-797-1823";
        int expected = 5;
        //When
        Map actual = phoneBook.addEntry(name, number);
        //Then
        Assert.assertEquals("An extra entry in the hashmap, increases the size from 4 to 5 ", expected, actual.size());
        Assert.assertNotNull("Assert that there is an entry of Dan Alixk in the phonebook", phoneBook.lookUp(name));
    }

    @Test
    public void addBadEntryTest() throws InvalidPhoneNumberFormatException {
        //Given
        String input = "Dan Alixk\n(0)-799933333333339999-03\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        PhoneBook phoneBook = new PhoneBook(byteArrayInputStream);
        String name = "Dan Alixk";
        String number = "(0)-799933333333339999-03";
        //When
        Map actual = phoneBook.addEntry(name, number);
        //Then
        Assert.assertEquals("This is a unformatted number, and should not pass", null, actual);
    }


    @Test
    public void listAllEntriesTest() {
        //Given
        PhoneBook phoneBook = new PhoneBook();
        String expected = "Ben Berger (802)-453-3582\n" +
                "Lisa Knelly (732)-914-8849\n" +
                "Sarah Silverman (302)-434-1849\n" +
                "Steve Johns (412)-791-1823";
        //When
        String actual = phoneBook.listAllEntries();
        //Then
        Assert.assertEquals("Test to make sure all entries are in the phonebook", expected, actual);
    }

    @Test(expected = InvalidPhoneNumberFormatException.class)
    public void removeEntryTest() throws RecordNotPresent {

        String input = "Ben Berger\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        PhoneBook phoneBook = new PhoneBook(byteArrayInputStream);
        String name = "Ben Berger";
        String expectedString = "{Lisa Knelly=(732)-914-8849, Sarah Silverman=(302)-434-1849, Steve Johns=(412)-791-1823}";

        int expected = 3;
        //When

        Map actual = phoneBook.removeEntry(name);
        //Then

        Assert.assertEquals("Removing Ben Berger will decrease the size from 4 to 3 ", expected, actual.size());
       // System.out.println(actual);
        //Assert.assertEquals(expectedString, actual);

    }

    @Test
    public void removingNonexistentTest() throws RecordNotPresent {

        String input = "Charlie Chaplin\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        PhoneBook phoneBook = new PhoneBook(byteArrayInputStream);
        String name = "Charlie Chaplin";

        //When
        Map actual = phoneBook.removeEntry(name);
        //Then

        Assert.assertEquals("Throw an exception if there isn't a person with said name in the phonebook", actual);
    }

    @Test
    public void listAllNamesTest() {
        //Given
        PhoneBook phoneBook = new PhoneBook();
        String expected = "Ben Berger\n" +
                "Lisa Knelly\n" +
                "Sarah Silverman\n" +
                "Steve Johns";
        //When
        String actual = phoneBook.listAllNames();
        //Then
        Assert.assertEquals("Test to make sure all the names are in the phonebook", expected, actual);
    }


    @Test
    public void reverseLookupTest() {
        //Given
        PhoneBook phoneBook = new PhoneBook();
        String expected = ;
        //When
        String actual = phoneBook.reverseLookUp("(732)-914-8849");
        //Then
        Assert.assertEquals("Test to make sure all the names are in the phonebook", expected, actual);
    }


}
