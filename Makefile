JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        ChatClient.java \
        ChatClientThread.java \
        ChatServer.java \
        ChatServerThread.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
