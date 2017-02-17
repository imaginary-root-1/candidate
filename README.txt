How to build and run:

There are three unix shell scripts included that will assist you in building
and running this project.

build: build the source code.

unittest: build the source code and run the unit tests.

run: build the source code and run the server.



Design Rationale:

I broke down the problem into three main components:


Unit Test:

I'd usually use junit, but according to the rules I'm not allowed to use
outside libraries. I rolled my own. The testing framework is contained
in UnitTest and IUnitTest, and I also wrote a test of the test framework,
TestUnitTest.


Package Manager:

A package manager manages a collection of Packages. This is implemented in
Package and PackageManager, and tested by TestPackageManager. I did not
write a unit test specifically for Package, as I consider it part
of PackageManager. Depending on the policies of the company, I could
write a unit test for Package, or move Package into PackageManager
to potentially avoid separate unit testing.

PackageManager access methods are synchronized to make them thread-safe.
Depending on typical usage and performance goals, this can be optimized.
For example, query() might not need to be synchronized (depending on
HashMap implementation).


Server:

A server needs a socket to accept connections, a handler for each connection,
and a parser for data coming in over the connection. This is implemented
in PackageServer, ClientHandler and CommandLine, and tested in
TestCommandLine. The server and client code do not have individual unit
tests, instead they are tested by the supplied integration test.
If unit testing is necessary this could be accomplished using a mock
framework.



All of these things can be accomplished through build systems such as
make, ant, gradle, etc., but individual shell scripts work well enough
when I have no idea what system this will be built and run on.



Notes:

github link: https://github.com/imaginary-root-1/candidate

Requires a JVM (and jdk if you want to build).

From my reading of the instructions, I did not see a definition of what
characters are valid in a package name. Also left unanswered is if
slightly broken input (i.e. whitespace, missing | when there are no
dependencies, etc) is supposed to be acceptable. I had to ascertain the
rules through reading the test source code and trial-and-error.

The server classes produce a lot of output related to communication
and formatting errors. In production code I would use logging levels
to allow for configurable restriction of messages.

I didn't use java packages, because I wanted to simplify the build.
In production code, the testing classes would be somewhere else, so
they wouldn't be pulled into the build.

I created a new github account for this project, and attempted to remove
any identifying information. I'm not an expert github user, so I might have
missed something. My current employer half-believes in open source (the
taking half), and I'm contractually forbidden to contribute to open
source projects. Did I mention their management practices seem to be
stuck in the (18)70s?
