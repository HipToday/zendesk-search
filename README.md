# zendesk-search
A Zendesk Coding Challenge

## USAGE
1. Download and install [Java SE 8 or later](https://www.oracle.com/technetwork/java/javase/downloads/index.html). Make sure the `JAVA_HOME` environment variable is set correctly and `JAVA_HOME/bin` is added to your `PATH` environment variable.
2. [Download](https://maven.apache.org/download.cgi) and [install](https://maven.apache.org/install.html) Apache Maven. Make sure the Maven `bin` directory is added to your `PATH` environment variable.
3. Run the following commands in the project root directory:

         mvn test compile assembly:single
         java -cp target/zendesk-search-1.0-SNAPSHOT-jar-with-dependencies.jar com.nicktempleton.zendesk.search.App

## OVERVIEW
Using the provided data ([tickets.json](./src/main/resources/tickets.json) and [users.json](./src/main/resources/users.json) and [organizations.json](./src/main/resources/organizations.json)) write
a simple command line application (or a locally runnable web-app) to search the data and return
the results in a human readable format.

When executing a search operation, where data exists, values from any related entities should be
included in the results. The user should be able to search on any field and exact value matching
is fine (e.g. “mar” won’t return “mary”). The user should also be able to search for empty values,
e.g. where description is empty.

Search can get pretty complicated pretty easily, we just want to see that you can code a basic
search application.

## EVALUATION CRITERIA
We will look at your project and assess it for:

1. Does It Work - Does the solution run per the instructions?
2. Design - Does the solution demonstrate good modularity, extensibility, and separation of
concerns? Are appropriate OOP/OOD principles employed?
3. Readability - Does the solution display proficiency with the language, a consistent style,
and is it understandable? Hint: Use of your favorite linter or static analysis tool highly
encouraged!
4. Testing - Do the tests pass, do they verify all the use cases, are they structured well, do
they break when unexpected changes are made?
5. Robustness - Does the solution work quickly, efficiently, and gracefully handle
unexpected situations?

If you have any questions about this criteria please ask.

## SPECIFICATIONS
- Use the language in which you are strongest.
- Feel free to use libraries or roll your own code as you see fit.
- Include a README with (accurate) usage instructions.

## HINTS
- A member of our team wrote a [blog about how to pass a coding challenge](http://chocolatetin.org/2015/08/08/how-to-pass-a-coding-test.html).
- The problem definition is pretty basic – if you wish to implement additional (or expanded)
functionality that would be awesome – please make note of any assumptions or design
decisions you make.
